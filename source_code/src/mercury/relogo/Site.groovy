package mercury.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;

import mercury.ReLogoTurtle
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;;

class Site extends ReLogoTurtle {
	
	int target_distribution = 0
	def trader_list = []
	
	boolean production_site = false
	boolean producer_A = false
	boolean producer_B = false
	boolean producer_C = false
	boolean producer_D = false

	int volume_A = 0
	int volume_B = 0
	int volume_C = 0
	int volume_D = 0
}
