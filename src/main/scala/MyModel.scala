import scala.actors._
import scala.actors.Actor._
import scala.actors.Actor

/* !! USER GUIDE (very important!):
		Please allow at least a few second(s) between your selections.
*/

object MyModel {

	

	var MaintenanceOn =false
	var AlarmOn= false
	var StopOn = false

	

//model can read system condition
  def isMaintenanceOn () = { MaintenanceOn}
  def isAlarmOn () = { AlarmOn}
  def isStopOn () = {StopOn}
//model can set system condition
	def setMaintenanceOn () = { MaintenanceOn = true}
  def setMaintenanceOff () = { MaintenanceOn = false}
	
	def setAlarmOn () = { AlarmOn= true}
	def setAlarmOff () = { AlarmOn = false}
	
  def setStopOnOff () = { 
  	StopOn = !StopOn
  	SystemStatus.elevatorStopButtonLit = ! SystemStatus.elevatorStopButtonLit
  }
//set the system status on/off  
  def setDoor1Open () = { SystemStatus.door1Open = true }
  def setDoor2Open () = { SystemStatus.door2Open = true }
  def setDoor3Open () = { SystemStatus.door3Open = true }

	def setDoor1Close() = { SystemStatus.door1Open = false}
	def setDoor2Close() = { SystemStatus.door2Open = false}
	def setDoor3Close() = { SystemStatus.door3Open = false}
  

 

//===============================================================
//3 Request lists



	var sameBefore = List[Int]()
	var oppDirection = List[Int]()
	var sameAfter = List[Int]()

//==============================================================
//direction and location attributes and methods
	var directionUp = false
	var directionDown = false
// 0 stop; 1 up; 2 down;
	def getCurrentDirection() :Int = {
		if (directionUp) return 1
		else if ( directionDown) return 2
		else return 0
	}
	var lastFloor:Int = 1
	def setLastFloor(n:Int) = { lastFloor = n}
	val floorHeight = List( 0, 36, 20 ,2)
	//Returns the amount of feet of cable the motor has let out.
		//36 ft is floor 1
		//20 ft is floor 2
		//2 ft is floor 3
	def getCurrentLocation() :Int= { Motor.lineOut() }

  //find the nearest floor in order to drop off all passengers
	def nearestFloor() :Int ={
		import scala.math.abs
		var floorNo:Int = List( abs(getCurrentLocation-36), abs(getCurrentLocation-20), abs(getCurrentLocation-2)).zipWithIndex.min._2 +1
		floorNo
	}
//=============================================================================
//modelActor helpers
def AlarmOnHelper :Unit ={
		allLightsOff()
		var TargetNo= nearestFloor()																// find the nearest floor
		if(floorHeight(TargetNo) > getCurrentLocation){				// if go down
			while (floorHeight(TargetNo) != getCurrentLocation){
				Motor.down()																				//move to target floor
			}															
		}
		else if(floorHeight(TargetNo) < getCurrentLocation){    //if go up
			while (floorHeight(TargetNo) != getCurrentLocation){
				Motor.up()																					//move to target floor
			}
		}
		Motor.stop()																						//motor stop																
		TargetNo match {																				//open the corresponding door
				case 1 => setDoor1Open ()
				case 2 => setDoor2Open ()
				case 3 => setDoor3Open ()
		}		
		directionUp=false																			//reset direction
		directionDown=false
}
// assume no lits is on during the process
def AlarmOffReset :Unit ={
	var currentFloor = floorHeight.indexOf(getCurrentLocation)
	currentFloor match {
		case 1 => 
		case 2 => while (getCurrentLocation != 36){
										setDoor2Close ()
										Motor.down()
									}
		case 3 => while (getCurrentLocation != 36){	
										setDoor3Close ()
										Motor.down()
									}					
	}
	Motor.stop()
	setLastFloor(1)
	setDoor1Open ()	
}
//go directly to floor 1
//precond: no requests in queues, and no more request 
def MaintenanceOnHelper:Unit={
	var currentFloor = floorHeight.indexOf(getCurrentLocation) 			//get the current floor
	currentFloor match {																						// close the current floor door
		case 2 => setDoor2Close ()
		case 3 => setDoor3Close ()
		case _ =>
	}
	if (currentFloor != 1){																			//go to floor 1
		while (getCurrentLocation != 36){
			Motor.down()
		}
		Motor.stop()
	}
	setDoor1Open ()																				//open the door
	setLastFloor(1)
	directionUp=false																					//reset the direction
	directionDown=false	
}

def MaintenanceOffReset :Unit={}                         // nothing need to be done, will add something if necessary later

def AlarmOnFlowChart :Unit ={
	AlarmOnHelper
	while(AlarmOn){
		Thread.sleep(180)
	}
	AlarmOffReset
}

def MaintenanceOnFlowChart:Unit={
	MaintenanceOnHelper
	while(MaintenanceOn){
		Thread.sleep(170)
	}
}

def execute(floorNo:Int) :Unit={
	lastFloor match{
		case 1 => setDoor1Close()
		case 2 => setDoor2Close()
		case 3 => setDoor3Close()
	}
	if(directionUp) {
		if (floorHeight(floorNo) != getCurrentLocation()){							
			Motor.up()
			SystemStatus.UpArrowOn = true	//up arrow light status follows the motor direction otherwise both up and down might light up (lightsHandling)		
		}
		else {
			Motor.stop()
			MyController.MsgActor ! "arrived"
			floorNo match {
				case 1 => {
							setDoor1Open()
							lightsOff(1) //(lightsHandling)
				}
				case 2 => {
							setDoor2Open()
							lightsOff(2) //(lightsHandling)
				}
				case 3 => {
							setDoor3Open()
							lightsOff(3) //(lightsHandling)
							SystemStatus.UpArrowOn = false //up arrow light's off when car hits the top floor since direction will change (lightsHandling)
				}				
			}
			Thread.sleep(2000)
		}
	}
	else if (directionDown){
		if (floorHeight(floorNo) != getCurrentLocation){
			Motor.down()
			SystemStatus.DownArrowOn = true    //down arrow light status follows the motor direction otherwise both up and down might light up (lightsHandling)
		}
		else{
			Motor.stop()
			MyController.MsgActor ! "arrived"
			floorNo match {
				case 1 => {
							setDoor1Open()
							lightsOff(1) //(lightsHandling)
							SystemStatus.DownArrowOn = false //down arrow light's off when car hits the first floor since direction will change (lightsHandling)
				}
				case 2 => {
							setDoor2Open()
							lightsOff(2) //(lightsHandling)
				}
				case 3 => {
							setDoor3Open()
							lightsOff(3) //(lightsHandling)
				}
			}
			Thread.sleep(2000)
		}
	}

	
}
//==============================================================================
//modelActor
	val ModelActor= actor{
		setDoor1Open ()
		Thread.sleep(1000)
		while(true){
			//Thread.sleep(500)
			while (StopOn){
				Motor.stop()
				Thread.sleep(100)
				if((MaintenanceOn) || (AlarmOn)) {
					StopOn=false
					}
			}
		
			if ( sameBefore.isEmpty && oppDirection.isEmpty && sameAfter.isEmpty){					//if all 3 lists are empty
				SystemStatus.UpArrowOn = false	// both up and down arrow should dim (lightsHandling)
				SystemStatus.DownArrowOn = false// when all the jobs are done (lightsHandling)
				if (AlarmOn) {																																//case 1: the alarm mode is on
					AlarmOnFlowChart
				}
				else if(MaintenanceOn){																									//case 2: the Maintenance mode is on
					MaintenanceOnFlowChart
				}					
				else{}																																	//case 3: system is idle, do nothing
			}
			else if (! (sameBefore.isEmpty)){																					//not all lists are empty, indicates sameBefore is not empty		
				execute (sameBefore.head)																							//execute the first floor number in sameBefore list
			}
			Thread.sleep(120)
		}
		
	}
//=======================================================================================================================
//                Lights Handlers
//=======================================================================================================================
	//Turn the lights on in regular mode when certain button is pressed
	//The light should not light up if the car is already on the related floor, but this logic is checked in myController
	val lightsOn = actor {
		while (true) {
			receive{
				case 1 => {SystemStatus.elevator1ButtonLit = true}
				case 2 => {SystemStatus.elevator2ButtonLit = true}
				case 3 => {SystemStatus.elevator3ButtonLit = true}
				case "1up" => {SystemStatus.floor1UpButtonLit = true}
				case "2up" => {SystemStatus.floor2UpButtonLit = true}
				case "2down" => {SystemStatus.floor2DownButtonLit = true}
				case "3down" => {SystemStatus.floor3DownButtonLit = true}				
			}
		}
	}

	//lightsOff is NOT an actor but simply an method called when the car reaches certain floor
	//called by execute()
	def lightsOff(floorNumber:Int){
		floorNumber match {
			case 1 => {
				SystemStatus.floor1UpButtonLit = false
				SystemStatus.elevator1ButtonLit = false
			}
			case 2 => {
				SystemStatus.floor2UpButtonLit = false
				SystemStatus.floor2DownButtonLit = false
				SystemStatus.elevator2ButtonLit = false
			}
			case 3 => {
				SystemStatus.elevator3ButtonLit = false
				SystemStatus.floor3DownButtonLit = false
			}
		}

	}

	//turn all lights off
  	def allLightsOff() :Unit ={
  		for (i <- 1 to 3){lightsOff(i)}
 	}
	def turnOff(floorNumber:Int){
		floorNumber match {
			case 1 => {SystemStatus.elevator1ButtonLit = false}
			case 2 => {SystemStatus.elevator2ButtonLit = false}
			case 3 => {SystemStatus.elevator3ButtonLit = false} 
		}
	}

}