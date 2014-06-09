object Motor {
	
		//This function tells the motor to go up.
		def up() =
		{
			guiGlobals.motor.up()
		}
		//This function tells the motor to go down.
		def down() = 
		{
			guiGlobals.motor.down()
		}
		//This function stops the motor.
		def stop() = 
		{
			guiGlobals.motor.stop()
		}

		//Returns the amount of feet of cable the motor has let out.
		//36 ft is floor 1
		//20 ft is floor 2
		//2 ft is floor 3
		def lineOut():Int = 
		{
			for(i <- 0 to 18)
			 if(guiGlobals.elevLocation.radios(i).selected) return i*2
		 return 0
		}
}