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

class Trader extends ReLogoTurtle {
	def on_site = null
	float avg_demand = 0
	
	int product_A = 0
	int product_B = 0
	int product_C = 0
	int product_D = 0
	int stock_A = 0
	int stock_B = 0
	int stock_C = 0
	int stock_D = 0
	int maximum_stock_size = 0
	float price = 0
	int demand = 0
	int cluster_number = 0
	def closeness = 0
	def betweenness = 0
	
	
	//Submodel C: traders determine demand
	
	//Increase the trader's demand by one if it is less than the maximum demand
	def determine_demand(){
		if(demand < maximum_demand){
			demand++
		}
	}
	
	//Submodel D: traders discard part of stock
	
	//Determine the amount of stock that will be dropped (14% of each)
	def discard_stock(){
		int A_dropped = round((stock_A/100)*14)
		int B_dropped = round((stock_B/100)*14)
		int C_dropped = round((stock_C/100)*14)
		int D_dropped = round((stock_D/100)*14)
		
		//Add the amount of stock that will be dropped to the volume of the trader's site (this represents "lost" pottery).
		ask(on_site){
			volume_A += A_dropped
			volume_B += B_dropped
			volume_C += C_dropped
			volume_D += D_dropped
		}

		//Set the product of each type (ie: all items that can be traded this loop) equal to the stock minus the amount dropped.
		product_A += stock_A - A_dropped
		product_B += stock_B - B_dropped
		product_C += stock_C - C_dropped
		product_D += stock_D - D_dropped
		
		//Set the stock of each product to 0
		stock_A = 0
		stock_B = 0
		stock_C = 0
		stock_D = 0
	}	
	
	//Submodel F: Traders obtain commercial information and estimate a price
	def obtain_info_estimate_price(){
		
		//Create a list of informants (a proportion of neighbors in the network)
		int ceilinginformants = ceiling(count(linkNeighbors()) * local_knowledge)
		def informants = nOf(ceilinginformants,linkNeighbors())	
		
		int total_demand = 0
		int total_supply = 0
		
		//Calculate the total demand and supply of all informants combined
		ask(informants){
			total_demand += demand
			total_supply += (product_A + product_B + product_C + product_D)
		}
		
		//Calculate the average demand and supply of the informants + this trader
		avg_demand = (total_demand + demand) / (count(informants) + 1)
		float avg_supply = (total_supply + (product_A + product_B + product_C + product_D)) / (count(informants) + 1)
		
		//Calculate the trader's price
		price = avg_demand / (avg_supply + avg_demand)
	}

	//Submodel G: Traders determine maximum stock
	
	//Set the maximum stock size equal to the average_demand of it's known network minus the trader's own demand.
	def determine_max_stock(){
		maximum_stock_size = round(avg_demand - demand)
	}
}
