import scala.actors.Actor
import scala.actors.Actor._

/* !! USER GUIDE (very important!):
		Please allow at least a few second(s) between your selections.
*/

object MyController {

		//Receives requests to bring car to # floor
		val FloorActor = actor {
				while(true) {
						receive {
								case (i:Int, j:Boolean) => {

										if (MyModel.isMaintenanceOn() || MyModel.isAlarmOn()) {}
	
										// insert_Request(target floor, going up?, inside elev.car?)
										else {
											insert_Request(i, j, false)
											if (i != getCurrentFloor()) { //we don't want the light on if the car is already on that floor (lightsHandling)
												if (i == 1){MyModel.lightsOn ! "1up"}
												if (i == 2 && j == true){MyModel.lightsOn ! "2up"}
												if (i == 2 && j == false){MyModel.lightsOn ! "2down"}
												if (i == 3){MyModel.lightsOn ! "3down"}
											}
										}
										}
								
						}
				}
		}

		//Receives requests (from inside car) to stop at # floor
		val ElevActor = actor {
				while(true) {
						receive {
								case i:Int => {
										if (MyModel.isMaintenanceOn() || MyModel.isAlarmOn()) {}
										else {
											insert_Request(i,false,true)
											if (i != getCurrentFloor()) { // we don't want the light on if the car is already on that floor (lightsHandling)
												MyModel.lightsOn ! i 
											}
										}
										//Thread.sleep(100)
							
								}
						}
				}
		}

		//Handles various messages
		val MsgActor = actor {
				while(true) {
						receive {
								// messages from GuiCommands
								case "stop" => MyModel.setStopOnOff()
								case "maint on" => MyModel.setMaintenanceOn()
								case "maint off" => MyModel.setMaintenanceOff()
								case "alarm on" => MyModel.setAlarmOn(); emptyAllLists()
								case "alarm off" => MyModel.setAlarmOff()
								
								// message from MyModel
								case "arrived" => {
										//precondition: sameBefore is not empty, car arrives at target floor										
										listRemover()
							
								}
								
						}
				}
		}	


	//var lastFloor:Int = 1
	//var targetFloor:Int=_

	//def setLastFloor(n:Int) = { lastFloor = n}
	//def setTargetFloor  = {targetFloor=sameBefore.head	}

	def emptyAllLists():Unit= { 
		MyModel.sameBefore=Nil
		MyModel.oppDirection=Nil
		MyModel.sameAfter=Nil
	}

//Returns the amount of feet of cable the motor has let out.
		//36 ft is floor 1
		//20 ft is floor 2
		//2 ft is floor 3
	def getCurrentLocation() :Int= { MyModel.getCurrentLocation() }
//determine if the car pass a certain floor 
	val floorHeight = List( 0, 36, 20 ,2)

//find the current floorNo
//precondition: it is right at a certain floor ,not somewhere between floors
	def getCurrentFloor() :Int = {
		if (getCurrentLocation() == 2) 3
		else if (getCurrentLocation() == 20) 2
		else if (getCurrentLocation() == 36) 1
		else -1 //dummy value
	}
	

	
//determine if the car pass the target floor
	def isPass (floorNo :Int) :Boolean = {
		if (MyModel.getCurrentDirection==1) // 0 stop; 1 up; 2 down;
			if (getCurrentLocation- floorHeight(floorNo) <0 ) return true else return false
		else if (MyModel.getCurrentDirection==2)// 0 stop; 1 up; 2 down;
			if (getCurrentLocation- floorHeight(floorNo) >0 ) return true else return false
		else return false
	}
	
//check the request from inside the car has the same direction as current motor direction
	def isSameDirection (floorNo:Int):Boolean ={
		if(MyModel.getCurrentDirection==1) // 0 stop; 1 up; 2 down;
			if(floorNo-MyModel.lastFloor > 0 ) return true else return false
		else if(MyModel.getCurrentDirection==2)// 0 stop; 1 up; 2 down;
			if(floorNo-MyModel.lastFloor < 0 ) return true else return false
		else return false
	}
//check the request from outside the car has the same direction as current motor direction,these two use dynamic binding
	def isSameDirection (GoUp_Outside:Boolean):Boolean ={
		if (MyModel.getCurrentDirection==1){ // 0 stop; 1 up; 2 down;
			return GoUp_Outside
		}
		else if (MyModel.getCurrentDirection==2){// 0 stop; 1 up; 2 down;
			return !GoUp_Outside
		}
		else return false
	}
//check if the car is at this floor ??need to modify later
	def isArrive (floorNo:Int) :Boolean = { floorHeight(floorNo) == getCurrentLocation()}
		
//insert_Request knows where to insert element
	def insert_Request (floorNo:Int, GoUp_Outside:Boolean, fromInside:Boolean ):Unit ={ 
		var isSameDirectionValue:Boolean = false
		if (! isArrive(floorNo) ) {
			if (MyModel.getCurrentDirection==0) {  								//if the car is idle
				if (MyModel.sameBefore.isEmpty)  {
					MyModel.sameBefore =floorNo::MyModel.sameBefore
					 
					if(floorHeight(floorNo)- getCurrentLocation >0) {
						MyModel.directionUp=false 
						MyModel.directionDown = true
					}
					else {MyModel.directionUp= true; MyModel.directionDown = false}
					return																												//the target floor put into sameBefore,change Direction
				}
			}
			if (fromInside) {																										//is this request from inside of ca?
				isSameDirectionValue = isSameDirection(floorNo)									// call the corresonding method
			}
			else{
				isSameDirectionValue = isSameDirection(GoUp_Outside)
			}
			if (isSameDirectionValue){																				//if the request has the same direction as the motor direction
				if (!MyModel.sameBefore.contains(floorNo)){														//if the floor number is not in the sameBefore list
					if (isPass(floorNo)){																			//if the car has passed this floor
						if(! (MyModel.sameAfter.contains(floorNo))) {											//if the floorNo is not in the sameAfter list
							MyModel.sameAfter = MyModel.sameAfter:+ floorNo
							if(MyModel.directionUp){															//append the floor at the end of sameAfter list
								MyModel.sameAfter= MyModel.sameAfter.sortWith(_<_)
							}
							else if(MyModel.directionDown) {
								MyModel.sameAfter = MyModel.sameAfter.sortWith(_>_)
							}
						}
					}
					else {																												//if the car not pass this floor
						if(! (MyModel.sameBefore.contains(floorNo))){											//if the floorNo is not in the sameBefore list
							MyModel.sameBefore = MyModel.sameBefore:+ floorNo											//append the floor at the end of sameBefore list
							if(MyModel.directionUp){
								MyModel.sameBefore = MyModel.sameBefore.sortWith(_<_)
							}
							else if(MyModel.directionDown) {
								MyModel.sameBefore= MyModel.sameBefore.sortWith(_>_)
							}
						}
						else {
							if(MyModel.directionDown) MyModel.sameBefore = MyModel.sameBefore.sortWith(_>_)
							if(MyModel.directionUp) MyModel.sameBefore= MyModel.sameBefore.sortWith(_<_)
						}
					}
				}
			}
			else{																													//if the car has oppsite direction
				if (! (MyModel.oppDirection.contains(floorNo))){										//the floorNo is not in the oppDir list
					MyModel.oppDirection = MyModel.oppDirection:+ floorNo												//append floorNo at the end of oppDir list
					if (MyModel.directionDown) MyModel.oppDirection = MyModel.oppDirection.sortWith (_<_)													//sort it with increase-order
					else if (MyModel.directionUp) MyModel.oppDirection= MyModel.oppDirection.sortWith(_>_)			//if the car is up,it indicates oppDir is down direction, so this list should be decrease-order
				}																													//if car is down, indicates oppDir is up direction, so the list should increase-order, no need to change
			}			
		}
	}
//===============================================================================
//listRemover : remove the first element in the sameBefore, paste oppDirection list into sameBefore if necessary
//precondition: sameBefore is not empty, car arrives at target floor
	def listRemover() :Unit={
		
		MyModel.lastFloor =MyModel.sameBefore.head
		MyModel.sameBefore = MyModel.sameBefore.tail																				//first remove the first element of sameBefore
		if (MyModel.sameBefore.isEmpty){																						//if sameBefore is empty
			MyModel.sameBefore= MyModel.oppDirection																					//shifts
			MyModel.oppDirection=MyModel.sameAfter
			MyModel.sameAfter=Nil
			
			if (MyModel.sameBefore.isEmpty) {																				//if the new sameBefore is empty	
				if(!(MyModel.oppDirection.isEmpty)){																			// if the new oppDir is not empty
					MyModel.sameBefore= MyModel.sameBefore:+ MyModel.oppDirection.head											//put the head one of oppDir into sameBefore
					MyModel.oppDirection = MyModel.oppDirection.tail
				}				
				else{																													// all these three lists are empty
					MyModel.directionUp=false																					//set the car idel
					MyModel.directionDown =false
					return
				}			
			}	
		if(floorHeight(MyModel.sameBefore.head)-getCurrentLocation>0) {MyModel.directionUp=false ; MyModel.directionDown=true}
		else {MyModel.directionUp=true; MyModel.directionDown=false}										//set direction
		}
					
	}
}