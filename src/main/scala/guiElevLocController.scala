import scala.actors.Actor
import scala.actors.Actor._
class guiElevLocController(elevLocation:guiElevLocation, door1:guiDoor, door2:guiDoor, door3:guiDoor) extends Actor
{
	// 0 stop; 1 up; 2 down;
	var status = 0
	def act()
	{
		while(true)
		{
		status match
		{
			case 0 => 
			case 1 => 
			{
				Thread.sleep(500)
				elevLocation.up
				if(door1.isOpen) door1.open //Re-renders dooors
	  		if(door2.isOpen) door2.open
	  		if(door3.isOpen) door3.open
				Thread.sleep(500)
			}
			case 2 =>
			{
				Thread.sleep(500)
				elevLocation.down
				if(door1.isOpen) door1.open //Re-renders doors
	  		if(door2.isOpen) door2.open
	  		if(door3.isOpen) door3.open
				Thread.sleep(500)
			}
			case _ => 
		}
		}
	}
	
	def up()
	{
		status = 1
	}
	
	def down()
	{
		status = 2
	}

	def stop()
	{
		status = 0
	}
	
}