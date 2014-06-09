import scala.actors.Actor
import scala.actors.Actor._

class GuiController extends Actor
{
		def act()
		{
			while(true)
			{
				Thread.sleep(100)
				if(SystemStatus.door1Open) GuiInput.Door1Open(); else GuiInput.Door1Close();
				if(SystemStatus.door2Open) GuiInput.Door2Open(); else GuiInput.Door2Close();
				if(SystemStatus.door3Open) GuiInput.Door3Open(); else GuiInput.Door3Close();
				if(SystemStatus.elevator1ButtonLit) GuiInput.Elevator1ButtonLightOn(); else GuiInput.Elevator1ButtonLightOff()
				if(SystemStatus.elevator2ButtonLit) GuiInput.Elevator2ButtonLightOn(); else GuiInput.Elevator2ButtonLightOff()
				if(SystemStatus.elevator3ButtonLit) GuiInput.Elevator3ButtonLightOn(); else GuiInput.Elevator3ButtonLightOff()
				if(SystemStatus.elevatorStopButtonLit) GuiInput.ElevatorStopButtonLightOn(); else GuiInput.ElevatorStopButtonLightOff()
				if(SystemStatus.floor1UpButtonLit) GuiInput.Floor1ButtonUpLightOn(); else GuiInput.Floor1ButtonUpLightOff()
				if(SystemStatus.floor2UpButtonLit) GuiInput.Floor2ButtonUpLightOn(); else GuiInput.Floor2ButtonUpLightOff()
				if(SystemStatus.floor2DownButtonLit) GuiInput.Floor2ButtonDownLightOn(); else GuiInput.Floor2ButtonDownLightOff()
				if(SystemStatus.floor3DownButtonLit) GuiInput.Floor3ButtonDownLightOn(); else GuiInput.Floor3ButtonDownLightOff()
				if(SystemStatus.UpArrowOn) guiGlobals.UpArrow.selected = true; else guiGlobals.UpArrow.selected = false;
				if(SystemStatus.DownArrowOn) guiGlobals.DownArrow.selected = true; else guiGlobals.DownArrow.selected = false;
				
				
			}
		}
}