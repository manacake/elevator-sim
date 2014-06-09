import scala.swing._
import event._
import Swing._
import java.awt.{Color}


class guiDoor(elevLocation: guiElevLocation, doorNumber:Int){

									
									var isOpen = false
									val leftDoorPanel = new Panel()
									{
    								border = LineBorder(Color.black)
    								background = Color.blue
    								preferredSize = (50,100)
    								maximumSize = (50,100)
    								minimumSize = (50,100)
    							}
    							
    							val rightDoorPanel = new Panel()
    							{
    								border = LineBorder(Color.black)
    								background = Color.blue
    								preferredSize = (50,100)
    								maximumSize = (50,100)
    								minimumSize = (50,100)
    							}

    							val panel = new BoxPanel(Orientation.Horizontal)
    							{
    								preferredSize = (100,100)
    								maximumSize = (100,100)
    								minimumSize = (100,100)
    								contents += leftDoorPanel
    								contents += rightDoorPanel
    							}

    							def open()
    							{
    								
    									isOpen = true
    									doorNumber match
    									{
    										case 3 => {
    											if(elevLocation.atFloor3)
    											{
    												leftDoorPanel.background = Color.green
    												rightDoorPanel.background = Color.green
    											}
    											else
    											{
    												leftDoorPanel.background = Color.red
    												rightDoorPanel.background = Color.red
    											}
    										}
    										case 2 =>
    										{
    											if(elevLocation.atFloor2)
    											{
    												leftDoorPanel.background = Color.green
    												rightDoorPanel.background = Color.green
    											}
    											else
    											{
    												leftDoorPanel.background = Color.red
    												rightDoorPanel.background = Color.red
    											}
    										}
    										case 1 =>
    										{
    											if(elevLocation.atFloor1)
    											{
    												leftDoorPanel.background = Color.green
    												rightDoorPanel.background = Color.green
    											}
    											else
    											{
    												leftDoorPanel.background = Color.red
    												rightDoorPanel.background = Color.red
    											}
    										}
    										case _ => 
    									}
    							
    							}

    							def close()
    							{
    								
    									isOpen = false
    									leftDoorPanel.background = Color.blue
    									rightDoorPanel.background = Color.blue
    								
    							}

}