object GuiInput{

	//This function opens up the door on floor 1.
	def Door1Open()
	{
			guiGlobals.door1.open()
	}

	//This function opens up the door on floor 2.
	def Door2Open()
	{
		guiGlobals.door2.open()
	}

	//This function opens up the door on floor3
	def Door3Open()
	{
		guiGlobals.door3.open()
	}

	//This function closes the door on floor 1.
	def Door1Close()
	{
			guiGlobals.door1.close()
	}

	//This function closes the door on floor 2.
	def Door2Close()
	{
		guiGlobals.door2.close()
	}

	//This function closes the door on floor3
	def Door3Close()
	{
		guiGlobals.door3.close()
	}

	//This function makes the elevator slowly rise.
	def ElevatorUp()
	{
		guiGlobals.motor.up()
	}

	//This function makes the elevator slowly move down.
	def ElevatorDown()
	{
		guiGlobals.motor.down()
	}

	//This function makes the elevator stop.
	def ElevatorStop()
	{
		guiGlobals.motor.stop()
	}

	def Elevator1ButtonLightOn()
	{
		guiGlobals.Floor1Button.lightOn()
	}

	def Elevator1ButtonLightOff()
	{
		guiGlobals.Floor1Button.lightOff()
	}

	def Elevator2ButtonLightOn()
	{
		guiGlobals.Floor2Button.lightOn()
	}

	def Elevator2ButtonLightOff()
	{
		guiGlobals.Floor2Button.lightOff()
	}

	def Elevator3ButtonLightOn()
	{
		guiGlobals.Floor3Button.lightOn()
	}

	def Elevator3ButtonLightOff()
	{
		guiGlobals.Floor3Button.lightOff()
	}

	def ElevatorStopButtonLightOn()
	{
		guiGlobals.StopButton.lightOn()
	}

	def ElevatorStopButtonLightOff()
	{
		guiGlobals.StopButton.lightOff()
	}

	def Floor1ButtonUpLightOn()
	{
		guiGlobals.upButton1.lightOn()
	}

	def Floor1ButtonUpLightOff()
	{
		guiGlobals.upButton1.lightOff()
	}

		def Floor2ButtonUpLightOn()
	{
		guiGlobals.upButton2.lightOn()
	}

	def Floor2ButtonUpLightOff()
	{
		guiGlobals.upButton2.lightOff()
	}
			
	def Floor2ButtonDownLightOn()
	{
		guiGlobals.downButton2.lightOn()
	}

	def Floor2ButtonDownLightOff()
	{
		guiGlobals.downButton2.lightOff()
	}

	def Floor3ButtonDownLightOn()
	{
		guiGlobals.downButton3.lightOn()
	}

	def Floor3ButtonDownLightOff()
	{
		guiGlobals.downButton3.lightOff()
	}

}