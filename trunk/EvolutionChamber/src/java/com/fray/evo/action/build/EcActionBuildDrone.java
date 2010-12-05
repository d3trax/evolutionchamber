package com.fray.evo.action.build;

import java.io.Serializable;

import com.fray.evo.EcBuildOrder;
import com.fray.evo.EcEvolver;
import com.fray.evo.util.GameLog;
import com.fray.evo.util.RunnableAction;
import com.fray.evo.util.Unit;
import com.fray.evo.util.UnitLibrary;

public final class EcActionBuildDrone extends EcActionBuildUnit implements Serializable
{
	public EcActionBuildDrone()
	{
		super(UnitLibrary.Drone);
	}

	@Override
	protected void postExecute(final EcBuildOrder s, final GameLog e)
	{
		s.AddUnits((Unit) buildable, 1);
		s.dronesGoingOnMinerals += 1;
		s.addFutureAction(2, new RunnableAction()
		{
			@Override
			public void run(GameLog e)
			{
				if (s.droneIsScouting == false && s.getDrones() >= s.scoutDrone
						&& s.scoutDrone != 0)
				{
					s.droneIsScouting = true;
					if (e.getEnable())
						e.printMessage(s, GameLog.MessageType.Scout, " +1 Scouting Drone");
				}
				else
				{
					s.dronesGoingOnMinerals--;
					s.dronesOnMinerals++;
				}
			}
		});
	}

}
