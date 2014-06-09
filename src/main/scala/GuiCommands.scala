/* !! USER GUIDE (very important!):
		Please allow at least a few second(s) between your selections.
*/

object guiOutput {

	def Floor1Up()
	{
		// Up button is pressed on floor 1 (outside elevator).
		MyController.FloorActor ! (1, true)
		println("Floor 1 Up Button Pressed")
	}
	
	def Floor2Up()
	{
		// Up button is pressed on floor 2 (outside elevator).
		MyController.FloorActor ! (2, true)
		println("Floor 2 Up Button Pressed")
	}

	def Floor2Down()
	{
		// Down button is pressed on floor 2 (outside elevator).
		MyController.FloorActor ! (2, false)
		println("Floor 2 Down Button Pressed")
	}

	def Floor3Down()
	{
		// Down button is pressed on floor 3 (outside elevator).
		MyController.FloorActor ! (3, false)
		println("Floor 3 Down Button Pressed")
	}

	def elevFloor1()
	{
		// Target floor 1 button is pressed inside the elevator
		MyController.ElevActor ! 1
		println("Elevator Button 1 Pressed")
	}

	def elevFloor2()
	{
		// Target floor 2 button is pressed inside the elevator
		MyController.ElevActor ! 2
		println("Elevator Button 2 Pressed")
	}

	def elevFloor3()
	{
		// Target floor 3 button is pressed inside the elevator
		MyController.ElevActor ! 3
		println("Elevator Button 3 Pressed")
	}

	def elevStop()
	{
		// Stop button is pressed in the elevator
		MyController.MsgActor ! "stop"
		println("Elevator Stop Button Pressed")
	}

	def MaintenanceModeOn()
	{
		//Place your code here for when the maintanence mode is switched to on.
		//send "maint on"
		MyController.MsgActor ! "maint on"
		println("Maintenance Mode On")
	}
	def MaintenanceModeOff()
	{
		//Place your code here for when the maintanence mode is switched to off.
		//send "maint off"
		MyController.MsgActor ! "maint off"
		println("Maintenance Mode Off")
	}

	def AlarmModeOn()
	{
		//Place your code here for when the alarm mode is switched to on.
		//send "alarm on"
		MyController.MsgActor ! "alarm on"
		println("Alarm On")
	}

	def AlarmModeOff()
	{
		//Place your code here for when the alarm mode is switched to off.
		//send "alarm off"
		MyController.MsgActor ! "alarm off"
		println("Alarm Off")
	}
}