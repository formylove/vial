package ink.moshuier.silken.task;
import java.util.TimerTask;
import com.opensymphony.xwork2.ActionContext;

import ink.moshuier.silken.common.TimeManager;

public class StartUpTask extends TimerTask {

	public StartUpTask(ActionContext acx){
		
	}
	public StartUpTask(){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method sub
		System.out.println("��ʼִ�������� " + TimeManager.getTime());
	}

}
