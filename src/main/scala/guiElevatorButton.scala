import java.awt.{Color}
import scala.swing._
import event._
import Swing._
class guiElevatorButton(a:Action) extends Button(a)
{

	def lightOn() =
	{
		 this.foreground = Color.orange
	}
	def lightOff() = 
			this.foreground = Color.black
}