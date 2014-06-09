/***********************************************
//   22c22: Object Oriented Software Development
//   Fall 2012
//  The University of Iowa
//
//   Instructors: Cesare Tinelli, Ryan McCleeary, Peng Fu
//************************************************/

import scala.swing._
import event._
import Swing._

import guiGlobals._



import java.awt.{Color}

object GUI extends SimpleSwingApplication {

		
    MyModel.ModelActor.start()
    guiGlobals.motor.start()
    guiGlobals.guiController.start()
    guiGlobals.mainMessenger.start()
	 // println(DateTime.now)
	 def ManualController = new Frame{
	  title = "Manual Controls"
	  contents = new FlowPanel()
	  	{
	  			val Floor1Open = Action("Door 1 Open")
	  			{
	  				SystemStatus.door1Open = true
	  			}
	  			val Floor2Open = Action("Door 2 Open")
	  			{
	  				SystemStatus.door2Open = true
	  			}
	  			val Floor3Open = Action("Door 3 Open")
	  			{
	  				SystemStatus.door3Open = true
	  			}
	  			val Floor1Close = Action("Door 1 Close")
	  			{
	  				SystemStatus.door1Open = false
	  			}
	  			val Floor2Close = Action("Door 2 Close")
	  			{
	  				SystemStatus.door2Open = false
	  			}
	  			val Floor3Close = Action("Door 3 Close")
	  			{
	  			 SystemStatus.door3Open = false
	  			}
	  			val ElevatorUp = Action("Elevator Up")
	  			{
	  				Motor.up()
	  			}
	  			val ElevatorDown = Action("Elevator Down")
	  			{
	  		  	Motor.down()
	  			}
	  			val ElevatorStop = Action("         Elevator Stop          ")
	  			{
	  		  	Motor.stop()
	  			}

	  		 val ElevLineOut = Action("     Cable Out of Elevator     ")
	  		 {
	  		 		println(Motor.lineOut());
	  		 }
	  		 val Elevator1ButtonOn = Action("Light Elevator 1")
	  		 {
	  		 		SystemStatus.elevator1ButtonLit = true
	  		 }
	  		 val Elevator1ButtonOff = Action("Dim Elevator 1")
	  		 {
	  		 		SystemStatus.elevator1ButtonLit = false
	  		 }
	  		  val Elevator2ButtonOn = Action("Light Elevator 2")
	  		 {
	  		 		SystemStatus.elevator2ButtonLit = true
	  		 }
	  		 val Elevator2ButtonOff = Action("Dim Elevator 2")
	  		 {
	  		 		SystemStatus.elevator2ButtonLit = false
	  		 }

	  		  val Elevator3ButtonOn = Action("Light Elevator 3")
	  		 {
	  		 		SystemStatus.elevator3ButtonLit = true
	  		 }
	  		 val Elevator3ButtonOff = Action("Dim Elevator 3")
	  		 {
	  		 		SystemStatus.elevator3ButtonLit = false
	  		 }

	  		  val ElevatorStopButtonOn = Action("Light Elevator Stop")
	  		 {
	  		 		SystemStatus.elevatorStopButtonLit = true
	  		 }
	  		 val ElevatorStopButtonOff = Action("Dim Elevator Stop")
	  		 {
	  		 		SystemStatus.elevatorStopButtonLit = false
	  		 }
	  		 
	  		 val Floor1UpButtonOn = Action("Light Floor1 Up")
	  		 {
	  		 		SystemStatus.floor1UpButtonLit = true
	  		 }
	  		 val Floor1UpButtonOff = Action("Dim Floor1 Up")
	  		 {
	  		 		SystemStatus.floor1UpButtonLit = false
	  		 }

	  		 	val Floor2UpButtonOn = Action("Light Floor2 Up")
	  		 {
	  		 		SystemStatus.floor2UpButtonLit = true
	  		 }
	  		 val Floor2UpButtonOff = Action("Dim Floor2 Up")
	  		 {
	  		 		SystemStatus.floor2UpButtonLit = false
	  		 }

	  		  	val Floor2DownButtonOn = Action("Light Floor2 Down")
	  		 {
	  		 		SystemStatus.floor2DownButtonLit = true
	  		 }
	  		 val Floor2DownButtonOff = Action("Dim Floor2 Down")
	  		 {
	  		 		SystemStatus.floor2DownButtonLit = false
	  		 }

	  		  	val Floor3DownButtonOn = Action("Light Floor3 Down")
	  		 {
	  		 		SystemStatus.floor3DownButtonLit = true
	  		 }
	  		 val Floor3DownButtonOff = Action("Dim Floor3 Down")
	  		 {
	  		 		SystemStatus.floor3DownButtonLit = false
	  		 }
	  		val UpArrowOn = Action("Up Arrow On")
	  		{
	  			SystemStatus.UpArrowOn = true
	  		}
	  			val UpArrowOff = Action("Up Arrow Off")
	  		{
	  			SystemStatus.UpArrowOn = false
	  		}
	  			val DownArrowOn = Action("Down Arrow On")
	  		{
	  			SystemStatus.DownArrowOn = true
	  		}
	  			val DownArrowOff = Action("Down Arrow Off")
	  		{
	  			SystemStatus.DownArrowOn = false
	  		}
	  		
	  		
	  			val elevUpButton = new Button(ElevatorUp)
	  			val elevDownButton = new Button(ElevatorDown)
	  			val elevStopButton = new Button(ElevatorStop)
	  		 val elevLineOutButton = new Button(ElevLineOut)
	  			val Floor1OpenButton = new Button(Floor1Open)
	  			val Floor2OpenButton = new Button(Floor2Open)
	  			val Floor3OpenButton = new Button(Floor3Open)
	  			val Floor1CloseButton = new Button(Floor1Close)
	  			val Floor2CloseButton = new Button(Floor2Close)
	  			val Floor3CloseButton = new Button(Floor3Close)
	  		 val Floor1LightOn = new Button( Elevator1ButtonOn)
	  		 val Floor1LightOff = new Button( Elevator1ButtonOff)
	  		 val Floor2LightOn = new Button( Elevator2ButtonOn)
	  		 val Floor2LightOff = new Button( Elevator2ButtonOff)
	  		 val Floor3LightOn = new Button( Elevator3ButtonOn)
	  		 val Floor3LightOff = new Button( Elevator3ButtonOff)
	  		 val StopLightOn = new Button( ElevatorStopButtonOn)
	  		 val StopLightOff = new Button( ElevatorStopButtonOff)
	  		 val up1LightOn = new Button (Floor1UpButtonOn)
	  			val up1LightOff = new Button (Floor1UpButtonOff)
	  			val up2LightOn = new Button (Floor2UpButtonOn)
	  			val up2LightOff = new Button (Floor2UpButtonOff)
	  			val down2LightOn = new Button (Floor2DownButtonOn)
	  			val down2LightOff = new Button (Floor2DownButtonOff)
	  			val down3LightOn = new Button (Floor3DownButtonOn)
	  			val down3LightOff = new Button (Floor3DownButtonOff)
	  			 val upArrowOnButton = new Button(UpArrowOn)
	  			 val upArrowOffButton = new Button(UpArrowOff)
	  			 val downArrowOnButton = new Button(DownArrowOn)
	  		 val downArrowOffButton = new Button(DownArrowOff)
	
	  			contents += elevUpButton
	  			contents += elevDownButton
	  			contents += elevStopButton
	  			contents += elevLineOutButton
	  			contents += Floor1OpenButton
	  			contents += Floor1CloseButton
	  			contents += Floor2OpenButton
	  			contents += Floor2CloseButton
	  			contents += Floor3OpenButton
	  			contents += Floor3CloseButton
	  		 contents += Floor1LightOn
	  		 contents += Floor1LightOff
	  		 contents += Floor2LightOn
	  		 contents += Floor2LightOff
	  		 contents += Floor3LightOn
	  		 contents += Floor3LightOff
	  		 contents += StopLightOn
	  		 contents += StopLightOff
	  		 contents += up1LightOn
	  		 contents += up1LightOff
	  		 contents += up2LightOn
	  		 contents += up2LightOff
	  		 contents += down2LightOn
	  		 contents += down2LightOff
	  		 contents += down3LightOn
	  		 contents += down3LightOff
	  	  	contents += upArrowOnButton
	  	  	contents += upArrowOffButton
	  	  	contents += downArrowOnButton
	  	  	contents += downArrowOffButton
	  	
	  		
	  			preferredSize = (330,600)
	 
	  	}
	 }

	
	 
  
  
  val mainFrame = new MainFrame {
  	menuBar = new MenuBar {
  		def mainMenu = new Menu("Open"){
  		val openManualControlsAction =  Action("Manual Controller")
    		{
    			ManualController.visible = true
    		}
   
    	contents += new MenuItem(openManualControlsAction)	
  		}
  		contents += mainMenu
  	}
    title = "Elevator Simulator"
   

    val MainPanel = new BoxPanel(Orientation.Horizontal) {
    	val ElevatorLocationPanel = new FlowPanel(new Label ("Location")){
    		val locationPanel = guiGlobals.elevLocation.panel
    		maximumSize = (100,600)
    		minimumSize = (100,600)
    		preferredSize = (100,600)
    		contents += locationPanel
    	}
    	val RightMainPanel = new BoxPanel(Orientation.Vertical){
    		val Floor3MainPanel = new BoxPanel(Orientation.Vertical)
    		{
    			border = LineBorder(Color.black)
    			val FloorLabelPanel = new FlowPanel(new Label ("Floor 3"))
    			{
    				maximumSize = (350,50)
    				minimumSize = (350,50)
    				preferredSize =(350,50)
    			}
    			val FloorDoorMainPanel = new BoxPanel(Orientation.Horizontal)
    			{
    				val FloorDoorPanel = new FlowPanel()
    				{
    					val verDoorBoxPanel = new BoxPanel(Orientation.Vertical){
    						val blankPanel = new FlowPanel(){
    							maximumSize = (250,50)
    							minimumSize = (250,50)
    							preferredSize = (250,50)
    						}
    						val mainDoorBoxPanel = new BoxPanel(Orientation.Horizontal){
    							val blankPanelLeft = new Panel(){
    								maximumSize = (75,100)
    								minimumSize = (75,100)
    								preferredSize = (75,100)
    							}
    							val doorPanel = guiGlobals.door3.panel
    							val blankPanelRight = new Panel(){
    								maximumSize = (75,100)
    								minimumSize = (75,100)
    								preferredSize = (75,100) 
    							}
    							contents += blankPanelLeft
    							contents += doorPanel
    							contents += blankPanelRight
    							
    						}
    						contents += blankPanel
    						contents += mainDoorBoxPanel
    					}
    					maximumSize = (250,150)
    					minimumSize = (250,150)
    					preferredSize = (250,150)
    					contents += verDoorBoxPanel
    				}
    				val FloorControlPanel = new FlowPanel(){

    					
    				

					   contents += guiGlobals.downButton3
    					
    					
    					maximumSize = (100, 150)
    					minimumSize = (100, 150)
    					preferredSize = (100, 150)
    				}
    				contents += FloorDoorPanel
    				contents += FloorControlPanel
    			}
    			contents += FloorLabelPanel
    			contents += FloorDoorMainPanel
    			preferredSize = (350,200)
    			maximumSize = (350,200)
    			minimumSize = (350,200)
    		}

    		val Floor2MainPanel = new BoxPanel(Orientation.Vertical)
    		{
    			border = LineBorder(Color.black)
    			val FloorLabelPanel = new FlowPanel(new Label ("Floor 2"))
    			{
    				maximumSize = (350,50)
    				minimumSize = (350,50)
    				preferredSize =(350,50)
    			
    			contents += guiGlobals.UpArrow;
    			contents += guiGlobals.DownArrow;
    			}
    			val FloorDoorMainPanel = new BoxPanel(Orientation.Horizontal)
    			{
    				val FloorDoorPanel = new FlowPanel()
    				{
    					val verDoorBoxPanel = new BoxPanel(Orientation.Vertical){
    						val blankPanel = new FlowPanel(){
    							maximumSize = (250,50)
    							minimumSize = (250,50)
    							preferredSize = (250,50)
    						}
    						val mainDoorBoxPanel = new BoxPanel(Orientation.Horizontal){
    							val blankPanelLeft = new Panel(){
    								maximumSize = (75,100)
    								minimumSize = (75,100)
    								preferredSize = (75,100)
    							}
    							val DoorPanel = guiGlobals.door2.panel
    							
    							val blankPanelRight = new Panel(){
    								maximumSize = (75,100)
    								minimumSize = (75,100)
    								preferredSize = (75,100) 
    							}
    							contents += blankPanelLeft
    							contents += DoorPanel
    							contents += blankPanelRight
    							
    						}
    						contents += blankPanel
    						contents += mainDoorBoxPanel
    					}
    					maximumSize = (250,150)
    					minimumSize = (250,150)
    					preferredSize = (250,150)
    					contents += verDoorBoxPanel
    				}
    				val FloorControlPanel = new FlowPanel(){
    					contents += guiGlobals.upButton2
    					contents += guiGlobals.downButton2
    					
    					maximumSize = (100, 150)
    					minimumSize = (100, 150)
    					preferredSize = (100, 150)
    				}
    				contents += FloorDoorPanel
    				contents += FloorControlPanel
    			}
    			contents += FloorLabelPanel
    			contents += FloorDoorMainPanel
    			preferredSize = (350,200)
    			maximumSize = (350,200)
    			minimumSize = (350,200)
    		}
    		val Floor1MainPanel = new BoxPanel(Orientation.Vertical)
    		{
    			border = LineBorder(Color.black)
    			val FloorLabelPanel = new FlowPanel(new Label ("Floor 1"))
    			{
    				maximumSize = (350,50)
    				minimumSize = (350,50)
    				preferredSize =(350,50)
    			}
    			val FloorDoorMainPanel = new BoxPanel(Orientation.Horizontal)
    			{
    				val FloorDoorPanel = new FlowPanel()
    				{
    					val verDoorBoxPanel = new BoxPanel(Orientation.Vertical){
    						val blankPanel = new FlowPanel(){
    							maximumSize = (250,50)
    							minimumSize = (250,50)
    							preferredSize = (250,50)
    						}
    						val mainDoorBoxPanel = new BoxPanel(Orientation.Horizontal){
    							val blankPanelLeft = new Panel(){
    								maximumSize = (75,100)
    								minimumSize = (75,100)
    								preferredSize = (75,100)
    							}
    							val DoorPanel = guiGlobals.door1.panel
    							val blankPanelRight = new Panel(){
    								maximumSize = (75,100)
    								minimumSize = (75,100)
    								preferredSize = (75,100) 
    							}
    							contents += blankPanelLeft
    							contents += DoorPanel
    							contents += blankPanelRight
    							
    						}
    						contents += blankPanel
    						contents += mainDoorBoxPanel
    					}
    					maximumSize = (250,150)
    					minimumSize = (250,150)
    					preferredSize = (250,150)
    					contents += verDoorBoxPanel
    				}
    				val FloorControlPanel = new FlowPanel(){
    				
    					contents += upButton1

    					
    					maximumSize = (100, 150)
    					minimumSize = (100, 150)
    					preferredSize = (100, 150)
    				}
    				contents += FloorDoorPanel
    				contents += FloorControlPanel
    			}
    			contents += FloorLabelPanel
    			contents += FloorDoorMainPanel
    			preferredSize = (350,200)
    			maximumSize = (350,200)
    			minimumSize = (350,200)
    		}
    		contents += Floor3MainPanel
    		contents += Floor2MainPanel
    		contents += Floor1MainPanel
    	}

   
		val elevatorPanel = new FlowPanel(new Label("Elevator Buttons"))
		{
		
			contents += guiGlobals.Floor3Button
			contents += guiGlobals.Floor2Button
			contents += guiGlobals.Floor1Button
			contents += guiGlobals.StopButton
			preferredSize = (100,150)
		}
		    val switchPanel = new BoxPanel(Orientation.Vertical)
		{
			
			
			val MaintenancePanel = new BoxPanel(Orientation.Horizontal)
			{
				val maintenanceControlOn = new RadioButton("Maintenance On")
			 val maintenanceControlOff = new RadioButton("Maintenance Off")

			 val maintenanceControlOnAction =  Action("Maintenance On")
			{
				mainMessenger ! "maintenanceModeOn"
			}
			 maintenanceControlOn.action = maintenanceControlOnAction;

			 val maintenanceControlOffAction =  Action("Maintenance Off")
			{
				mainMessenger ! "maintenanceModeOff"
			}
			 maintenanceControlOff.action = maintenanceControlOffAction;

			 val MaintenanceSwitch = new ButtonGroup{
				 buttons += maintenanceControlOn
				 buttons += maintenanceControlOff
			 }
			 maintenanceControlOff.selected = true;
			 contents += maintenanceControlOn
			 contents += maintenanceControlOff
			}
			val AlarmPanel = new BoxPanel(Orientation.Horizontal)
			{
				val AlarmControlOn = new RadioButton("Alarm On")
			 val AlarmControlOff = new RadioButton("Alarm Off")

			val AlarmControlOnAction =  Action("Alarm On")
			{
				mainMessenger ! "alarmModeOn";
			}
			 AlarmControlOn.action = AlarmControlOnAction;

			 val AlarmControlOffAction =  Action("Alarm Off")
			{
				mainMessenger ! "alarmModeOff";
			}
			 AlarmControlOff.action = AlarmControlOffAction;

			 val AlarmSwitch = new ButtonGroup{
				 buttons += AlarmControlOn
				 buttons += AlarmControlOff
			 }
			 AlarmControlOff.selected = true;
			 contents += AlarmControlOn
			 contents += AlarmControlOff
			}
			contents += MaintenancePanel
			contents += AlarmPanel
			
		}
	
    	contents += ElevatorLocationPanel
    	contents += RightMainPanel
    	contents += elevatorPanel
		contents += switchPanel
    	preferredSize = new Dimension(850,600)
    }
    
		
		
    contents = MainPanel
    }
    

    // Main frame contents
    
    
  
  def top = mainFrame
}





		