package mercury.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import mercury.ReLogoObserver;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.graph.ShortestPath;
import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;

import java.io.File;


/*
This is a replication of the MERCURY model by Brughmans and Poblome (2016)
The replication was originally written for a MSc thesis by Kanters (2019), which can be found here: https://openaccess.leidenuniv.nl/handle/1887/68248
In this version, several alterations were made to facilitate sensitivity analysis.

Brughmans, T. and J. Poblome, 2016. MERCURY: an agent-based model of tableware trade in the Roman East. Journal of Artificial Societies and Social Simulation 19 (1), https://doi.org/10.18564/jasss.2953.
https://www.comses.net/codebases/4347/releases/1.1.0/
*/


class UserObserver extends ReLogoObserver{
	//Link counters are used to record the number of links created during setup
	int circle_link_counter = 0
	int inter_link_counter = 0
	int intra_link_counter = 0
	int mutual_link_counter = 0
	int connect_components_link_counter = 0
	
	//The following variables are created for use elsewhere in the code
	def cluster_map = [:]
	def production_sites = []
	int total_pairs = 0
	int to_intra_link = 0
	def network 											
	float average_degree
	
	//Clustering coefficient and average shortest path distance as output variables
	float clustering_coefficient
	float average_shortest_path_distance
	
	
	@Setup
	def setup(){
		resetTimer()
		clearAll()															
		
		//Submodel A: select production sites and distribute traders on sites
		
		//A predetermined number of sites are created and moved forward
		//This creates are circle, since the agent's heading value is in order of ID
		setDefaultShape(Site,"house")
		createOrderedSites(num_sites){										
			fd(40)															
			setColor(5)
		}
		
		///A spacing variable is used to ensure production sites are equally distributed along the circle
		def spacing = round(num_sites / 4)									
		ask(item(0,sites())){
			setColor(15)
			production_site = true
			producer_A = true
		}
		
		ask(item(spacing,sites())){
			setColor(45)
			production_site = true
			producer_B = true
		}
		
		ask(item(spacing*2,sites())){
			setColor(65)
			production_site = true
			producer_C = true
		}
		
		ask(item(spacing*3,sites())){
			setColor(105)
			production_site = true
			producer_D = true
		}
		
		//A list of all production sites is created for use elsewhere in the code
		production_sites = sites().with{production_site == true}

		
		//Create traders
		setDefaultShape(Trader,"person")
		createTraders(num_traders)
				
		def non_producer_list = sites().with{production_site == false}
		
		//Calculate total possible number of trader for use elsewhere in the code
		total_pairs = 0.5*count(traders())*(count(traders())-1)			
		
		//Equal distribution to production sites
		if(equal_traders_production_site == true){
			//Link traders to production sites
			for(i in production_sites){
				ask(i){
					createSiteLinksTo(nOf(traders_production_site,traders().with{myInSiteLinks().size()==0}))
				}
			}
			
			
			//Uniform distribution to remaining sites
			if(traders_distribution == "uniform"){
				ask(non_producer_list){
					//First a uniform distribution target is set for each non-production site
					target_distribution = ceiling(traders().with{myInSiteLinks().size()==0}.size()/num_sites)
				}
				
				ask(traders().with{myInSiteLinks().size()==0}){
					
					if(non_producer_list.with{myOutSiteLinks().size() < target_distribution}.size() == 0){
						createSiteLinkFrom(oneOf(non_producer_list))
					}
					//If some non-production sites have not yet reached their target distribution, trader moves to one of them
					else{
						createSiteLinkFrom(oneOf(non_producer_list.with{myOutSiteLinks().size() < target_distribution}))
					}
				}
			}
			
			//Exponential distribution to remaining sites
			else{
				//First an exponential distribution target is set for each non-production site
				ask(non_producer_list){										
					target_distribution = ceiling((-1*(traders().with{myInSiteLinks().size()==0}.size())/num_sites) * ln(randomFloat(1.0)))
				}

				ask(traders().with{myInSiteLinks().size()==0}){
					//If all non-production sites already reached their target distribution, trader moves to a random site
					if(non_producer_list.with{myOutSiteLinks().size() < target_distribution}.size() == 0){
						createSiteLinkFrom(oneOf(non_producer_list))
					}
					//If some non-production sites have not yet reached their target distribution, trader moves to one of them
					else{												
						createSiteLinkFrom(oneOf(non_producer_list.with{myOutSiteLinks().size() < target_distribution}))
					}
				}
			}
		}
		
		//No equal distribution to production sites
		else{
			//If one_production_site_dominance is set to a value other than 0, that amount of traders are linked to production site A.
			//If one_production_site_dominance is 0, this part is ignored.
			if(one_production_site_dominance != 0){							
				ask(production_sites){
					if (producer_A == true){
						createSiteLinksTo(nOf(one_production_site_dominance,traders().with{myInSiteLinks().size()==0}))	
					}
					else{
						createSiteLinkTo(oneOf(traders().with{myInSiteLinks().size()==0}))
					}
				}
			}
			
			//A site list is created that is equal to all sites with no links, which is used to link traders to.
			//(This is needed in case one_production_site_dominance > 0)
			def site_list = sites().with{myOutSiteLinks().size()==0}
			
			//Uniform distribution, see documentation above
			if(traders_distribution == "uniform"){				
				ask(site_list){
					target_distribution = ceiling(traders().with{myInSiteLinks().size()==0}.size()/num_sites )
				}
				
				ask(traders().with{myInSiteLinks().size()==0}){
					if(site_list.with{myOutSiteLinks().size() < target_distribution}.size() == 0){
						//I originally assumed a link should be created to any trader (ie site_list) because equal_traders_production_site == false
						//But in the original model, a link is created to a non-production site
						createSiteLinkFrom(oneOf(non_producer_list))
					}
					else{
						createSiteLinkFrom(oneOf(site_list.with{myOutSiteLinks().size() < target_distribution}))
					}
				}
			}
			
			//Exponential distribution, see documentation above
			else{
				ask(site_list){	
					target_distribution = ceiling((-1*(traders().with{myInSiteLinks().size()==0}.size())/num_sites) * ln(randomFloat(1.0)))
				}
				ask(traders().with{myInSiteLinks().size()==0}){							
					if(site_list.with{myOutSiteLinks().size() < target_distribution}.size() == 0){
						createSiteLinkFrom(oneOf(non_producer_list))
					}
					else{
						createSiteLinkFrom(oneOf(site_list.with{myOutSiteLinks().size() < target_distribution}))
					}
				}
			}
		}
		
		//Two variables are set for use elsewhere in the code
		//Each trader has a on_site variable containing the ID of the site they're located on (also moves the trader to this site for visualisation purposes)	
		ask(traders()){
			assert myInSiteLinks().size() == 1
			on_site = oneOf(inSiteLinkNeighbors())
			moveTo(on_site)
			}
		
		//Each site has a trader list containing all the traders located on the site
		ask(sites()){
			trader_list = outSiteLinkNeighbors()
		}
		
		
		//Submodel B: connect traders
		
		//Creating of the hypothesised network
		
		//1. Connect one trader per site in circle
		
		//Create a list of all sites to "ask" stuff to, because of how groovy works, this list is sorted by ID
		def site_list = sites()
		//Iterate this list to connect a random trader from each site to a random trader from the next site		
		for(i in site_list){
			if(i != last(site_list)){
				ask(i){
					def linker = oneOf(trader_list)	
					ask(item((site_list.findIndexOf{it == i} + 1),site_list)){
						def linkto = oneOf(trader_list)	
						ask(linker){
							createLinkWith(linkto)
							circle_link_counter++
						}
					}
				}
			}
			//Link a trader from the last site in the list to a trader from the first site, to complete the circle
			else{
				ask(i){	
					def linker = oneOf(trader_list)
						ask(item(0,site_list)){
						def linkto = oneOf(trader_list)
						ask(linker){
							createLinkWith(linkto)
							circle_link_counter++
						}
					}
				}
			}
		}
		
		//2. Create-inter-site links
		
		//Calculate the amount of inter-site links that have to be created (a proportion of total possible links)
		int to_inter_link = ceiling(total_pairs * proportion_inter_site_links)
		//Repeat linking while the amount to be linked is more than 0,
		//Each iteration a link is created with the following conditions: 
		//between two traders on different sites; both of them have a number of links less than maximum degree; they aren't linked yet.
		//At the end of the iteration, the amount of traders to be linked is reduced by one.
		while(to_inter_link > 0){
			def linker = oneOf(traders().with{linkNeighbors().size() < maximum_degree})
			ask(linker){
				def linkto = oneOf(other(traders().with{linkNeighbors().contains(linker) == false && on_site != linker.on_site && linkNeighbors().size() < maximum_degree}))
				createLinkWith(linkto)
				to_inter_link--
				inter_link_counter++
			}
		}
				
		//3. Create intra-site links 
		//and 4. Pair mutual neighbors
		
		//Calculate the sum of all links between traders
		int total_links = 0
		ask(traders()){
			total_links += count(linkNeighbors())
		}
		//Calculate avg_degree (average amount of links per trader)
		float avg_degree = total_links / count(traders())
		//Calculate amount of traders selected to potentially create intra-site links (a proportion of total possible links)
		to_intra_link = round(total_pairs * proportion_intra_site_links)	
		
		//A loop counter is created to check how many times the while loop runs.
		//This was used as a measure to stop the run if it gets stuck in this phase.
		//This part was added for the sensitivity analysis version and wasn't there in the original model.
		def loop_counter = 0
		
		//Repeat creating intra-site links and pairing mutual neighbors while the average degree is less than maximum_degree minus 10%
		while(avg_degree <= (maximum_degree * 0.9)){							
			create_intra_links()
			pair_mutual_neighbors()
				
			total_links = 0
			ask(traders()){
				total_links += count(linkNeighbors())
			}
			//Recalculate average degree
			avg_degree = total_links / count(traders())
			loop_counter++
			
			//If the loop is ran more than 500 times, the setup is deemed stuck and the run is aborted
			if(loop_counter > 500) {
				Parameters p = RunEnvironment.getInstance().getParameters()
				int ticks = ticks()
				def random_seed = p.getValue("randomSeed")
				def site_dict = "avg_degree stuck"
				def ln = System.getProperty('line.separator')
				
				//The following line must be altered to refer to the outputParamaterSweep.csv
				def outputFile = new File('PATH\\TO\\REPAST\\WORKSPACE\\MERCURY\\output\\outputParameterSweep.csv')

				outputFile.append(ln + random_seed + ";" + loop_counter + ";" + equal_traders_production_site + ";" + traders_distribution + ";" + network_structure + ";" + proportion_inter_site_links + ";" + proportion_intra_site_links + ";" + proportion_mutual_neighbors + ";" +  traders_production_site + ";" + one_production_site_dominance + ";" + maximum_degree + ";" + local_knowledge +  ";" + maximum_demand + ";" + circle_link_counter + ";" + inter_link_counter + ";" + intra_link_counter + ";" + mutual_link_counter + ";" + connect_components_link_counter + ";" + average_degree + ";" + clustering_coefficient + ";" + average_shortest_path_distance + ";" + site_dict)
				print("avg_degree stuck, aborting run") 
				break
			}
		}
		
		if(loop_counter > 500) {
			stop()
		}
		
		//5. Connect components
		connect_components()
		
		
		//Creating of the random network
		//The number of links counted during the hypothesised network creation are counted, destroyed and the same amount of random links are created.
		//The connect components procedure is ran again to ensure the network consists of one big cluster
		if(network_structure == "random"){
			int amount_pairs = count(links())								
			ask(links()){
				die()
			}
			while(amount_pairs > 0){
				def linker = oneOf(traders())
				ask(linker){
					def linker_links = linkNeighbors()
					def linkto = oneOf(other(traders()))
					if(linker_links.contains(linkto) == false){				
						createLinkWith(linkto)
						amount_pairs--
					}
				}
			}
			connect_components()
		}
				
		average_degree = getAverageDegree()
		clustering_coefficient = getClusteringCoefficient()
		average_shortest_path_distance = getAverageShortestPathDistance()
	}
	
	//Procedure to create intra-site links
	def create_intra_links(){
		//Repeats the following process an amount of times equal to to_intra_link
		//First, find a random trader. Then, if there are other traders on the same site as that trader, select a second trader from that site. 
		//Then link them if: they aren't already linked and both of them have less links than maximum_degree
		repeat(to_intra_link){
			def linker = oneOf(traders())
			ask(linker){
				if(count(traders().with{on_site == linker.on_site})>1){
					def linkto = oneOf(other(traders()).with{on_site == linker.on_site})
					int linker_link_count = linkNeighbors().size()
					int linktolinkcount = linkto.linkNeighbors().size()
					def linker_links = linkNeighbors()
					if (linker_links.contains(linkto) == false && 
						linker_link_count < maximum_degree && 
						linktolinkcount < maximum_degree){	
						createLinkWith(linkto)
						intra_link_counter++
					}
				}
			}
		}
	}
	
	//Procedure to pair mutual neighbors	
	def pair_mutual_neighbors(){
		//Determine the amount of traders to be selected for linking (a proportion of all traders with mutual neighbors)
		int sumlinkpairs = 0
		ask(traders()){
			sumlinkpairs += (linkNeighbors().size()*(linkNeighbors().size()-1))
		}
		def n_traders_to_select = round((0.5 * sumlinkpairs) * proportion_mutual_neighbors)
		
		//Make a probability list of traders who may potentially be linked
		def trader_distribution_list = []
		ask(traders()){
			def this_trader = it
			repeat(linkNeighbors().size()*(linkNeighbors().size()-1)){
				trader_distribution_list.add(this_trader)
			}
		}
		
		//Repeat the pairing process an amount of time equal to the previously calculated value
		//First, select a trader from the list. Then, make a subset of traders on the same site with an amount of links less than maximum degree.
		//If this subset contains any traders, select one of them as the "linker". Then, create another of traders from the last one but without a link to the "linker".
		//If this subset contains traders, select one of them and make a link between it and the "linker".
		repeat(n_traders_to_select){
			def selected_trader = oneOf(trader_distribution_list)
			ask(selected_trader){
				def possible_mutual_neighbors = linkNeighbors().with{on_site == selected_trader.on_site && linkNeighbors().size() < maximum_degree}
				if (possible_mutual_neighbors.size() > 0){
					def linker = oneOf(possible_mutual_neighbors)
					ask(linker){
						def possible_linkto = other(possible_mutual_neighbors.with{linkNeighbors().contains(linker) == false})
						if(possible_linkto.size() > 0){
							createLinkWith(oneOf(possible_linkto))
							mutual_link_counter++
						}
					}
				}
			}
		}
	}
	
	//Procedure to connect components
	def connect_components(){
		//Identification of clusters is defined in another procedure.
		identify_clusters()
		
		//While loop that continues until there is only one cluster.
		while(cluster_map.size()>1){
			//First, create a list of sites that have traders with different cluster numbers on them
			def multiple_component_sites = []
			ask(sites()){
				def current_site = it
				def f = minOneOf(traders().with{on_site == current_site}){cluster_number}.cluster_number
				def g = maxOneOf(traders().with{on_site == current_site}){cluster_number}.cluster_number
				if(f != g){
					multiple_component_sites.add(current_site)
				}
			}
			
			//If the list of sites that have traders on it from different clusters is greater than one,
			//link two traders from different clusters on the same site to each other.
			if(multiple_component_sites.size()>0){
				ask(oneOf(multiple_component_sites)){
					def current_site = it
					def f = minOneOf(traders().with{on_site == current_site}){cluster_number}.cluster_number
					def g = maxOneOf(traders().with{on_site == current_site}){cluster_number}.cluster_number
					if(f != g){
						ask(oneOf(traders().with{on_site == current_site && cluster_number == f})){
							createLinkWith(oneOf(traders().with{on_site == current_site && cluster_number == g}))
							connect_components_link_counter++
						}
					}
				}
			}
			
			//If the list of sites that have traders on it from different clusters is not larger than one,
			//it means that there is one trader alone on a site that is not connected to any other trader 
			//(only happens in a random network, since in the hypothesised one, the traders are linked in a circle).
			//If this is the case, link this trader to a random other trader.
			else{
				ask(oneOf(traders().with{linkNeighbors().size()==0})){
					createLinkWith(oneOf(other(traders())))	
					connect_components_link_counter++
				}
			}
			identify_clusters()
		}
		network = getNetwork("UndirectedLinks")
	}
	
	//Procedure to identify clusters
	def identify_clusters(){
		network = getNetwork("UndirectedLinks")
		//Use the WeakComponentClusterer API to extract the clusters based on the graph
		//This is a list of lists of agents
		def clusters = new WeakComponentClusterer().transform(network.getGraph())
		def cluster_counter = 0
		def cluster_list = []
		
		//Iterate the cluster to filter out lists of non-trader agents (i.e. sites and links)
		for(i in clusters){
			def check_trader = true
			for(x in i){
				if(isTraderQ(x) == false){
					check_trader = false
				}
			}
			//If the item is a list of traders, add it to the cluster list
			if(check_trader == true){
				cluster_counter++
				cluster_list.add(i)
			}
		}
		
		//Create cluster map, to give each cluster a number
		cluster_map = [:]
		while(cluster_counter > 0){
			cluster_map.put(cluster_counter, cluster_list.get(cluster_counter - 1))
			cluster_counter--
		}
		
		//Assign cluster numbers to each trader based on the key in the cluster map
		cluster_map.each{ entry ->
			for(i in entry.value){
				ask(i){
					cluster_number = entry.key
				}
			}
		}
	}
	

	
	
	
	@Go
	def go(){
		tick()
		
		//The following procedures are looped until the maximum number of ticks is reached, which is 5000 in this case.
		//(In the original study this was 20000, but it was reduced to 5000 in the sensitivity analysis version, since the difference in the end states
		//was negligible and it would matter a lot in terms of processing time.)
		//The maximum number of ticks can be set in SimBuilder.groovy
		
		//determine_demand(), discard_stock(), obtain_info_estimate_price() and determine_max_stock() are defined in Trader.groovy
		//produce() and trader_consume() are defined later in this file.
		
		ask(traders()){
			determine_demand()												
		}
		ask(traders()){
			discard_stock()
		}
		
		produce()
		
		ask(traders()){
			obtain_info_estimate_price()
		}
		ask(traders()){
			determine_max_stock()
		}
		trade_consume()
	}
	
	//Submodel E: Traders at production sites produce
	//All traders on all production sites set their respective product to the demand value minus the sum of all their products
	def produce(){
		ask(production_sites){											
			if (producer_A == true){
				ask(trader_list){
					if ((product_A + product_B + product_C + product_D) < demand ){
						product_A += round(demand - (product_A + product_B + product_C + product_D))
					}
				}
			}													
			if (producer_B == true){
				ask(trader_list){
					if ((product_A + product_B + product_C + product_D) < demand ){
						product_B += round(demand - (product_A + product_B + product_C + product_D))
					}
				}
			}
			if (producer_C == true){
				ask(trader_list){
					if ((product_A + product_B + product_C + product_D) < demand ){
						product_C += round(demand - (product_A + product_B + product_C + product_D))
					}
				}
			}
			if (producer_D == true){
				ask(trader_list){
					if ((product_A + product_B + product_C + product_D) < demand ){
						product_D += round(demand - (product_A + product_B + product_C + product_D))
					}
				}
			}
		}
	}
	
	//Submodel H: Traders trade and consume
	def trade_consume(){
		//First, calculate the sum of all the trader's products of each type, as well as the total sum of all types combined
		int sum_all_products = 0
		int sum_product_A = 0
		int sum_product_B = 0
		int sum_product_C = 0
		int sum_product_D = 0
		ask(traders()){
			sum_all_products += product_A + product_B + product_C + product_D
			sum_product_A += product_A
			sum_product_B += product_B
			sum_product_C += product_C
			sum_product_D += product_D
		}
		
		//Perform this loop while there are still products to be traded.
		while(sum_all_products > 0){
			//First, choose a random product type to trade.
			char rand_product = oneOf(["A","B","C","D"])
			if(rand_product == "A" && sum_product_A > 0){
				//Then, determine a potential sellers from traders that still have product X
				ask(oneOf(traders().with{product_A > 0})){					//determine a seller from the potential sellers (traders with product A)
					//If there are no potential buyers (traders in the seller's network with stock size left and demand greater than 0):
					//Store the product, ie increase the stock of this product, remove this amount from maximum stock size, sum of all product and sum of product X and set it to 0.
					if(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}.size() == 0){
						stock_A += product_A
						maximum_stock_size -= product_A
						sum_all_products -= product_A
						sum_product_A -= product_A
						product_A = 0
					}
					//If there are potential buyers:
					//Select the trader with the highest price as the buyer.
					else{
						def buyer = maxOneOf(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}){
							price
						}
						
						//If the buyer's price is higher or equal to the seller's price, they trade one item.
						//ie: reduce product of type X from seller,sum of all products and sum of product X by 1.
						if(buyer.price >= price){
							product_A--	
							sum_all_products--
							sum_product_A--
							
							//If there are is no demand in the buyer's network, it stores it in stock.
							//ie: increase stock of product X by one and reduce maximum stock size by one
							ask(buyer){
								if(demand == 0){
									stock_A += 1
									maximum_stock_size -= 1	
								}
								//If there is demand, the product is consumed
								//ie: increase the volume of product X on the buyer's site by one
								else{
									demand--
									ask(on_site){
										volume_A++
									}
								}
							}
						}
						//If the buyer's price is lower than the seller's price, it stores the product (same as above)
						else{
							stock_A += product_A
							maximum_stock_size -= product_A
							sum_all_products -= product_A
							sum_product_A -= product_A
							product_A = 0
						}
					}
				}
			}
			
			//The same procedure is repeated for product B, C and D.
			if(rand_product == "B" && sum_product_B > 0){
				ask(oneOf(traders().with{product_B > 0})){
					if(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}.size() == 0){
						stock_B += product_B
						maximum_stock_size -= product_B
						sum_all_products -= product_B
						sum_product_B -= product_B
						product_B = 0
					}
					else{
						def buyer = maxOneOf(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}){
							price
						}
						if(buyer.price >= price){
							product_B--
							sum_all_products--
							sum_product_B--
							ask(buyer){
								if(demand == 0){
									stock_B += 1
									maximum_stock_size -= 1
								}
								else{
									demand--
									ask(on_site){
										volume_B++
									}
								}
							}
						}
						else{
							stock_B += product_B
							maximum_stock_size -= product_B
							sum_all_products -= product_B
							sum_product_B -= product_B
							product_B = 0
						}
					}
				}
			}

			if(rand_product == "C" && sum_product_C > 0){
				ask(oneOf(traders().with{product_C > 0})){
					if(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}.size() == 0){
						stock_C += product_C
						maximum_stock_size -= product_C
						sum_all_products -= product_C
						sum_product_C -= product_C
						product_C = 0
					}
					else{
						def buyer = maxOneOf(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}){
							price
						}
						if(buyer.price >= price){
							product_C--
							sum_all_products--
							sum_product_C--
							ask(buyer){
								if(demand == 0){
									stock_C += 1
									maximum_stock_size -= 1
								}
								else{
									demand--
									ask(on_site){
										volume_C++
									}
								}
							}
						}
						else{
							stock_C += product_C
							maximum_stock_size -= product_C
							sum_all_products -= product_C
							sum_product_C -= product_C
							product_C = 0
						}
					}
				}
			}
			
			if(rand_product == "D" && sum_product_D > 0){
				ask(oneOf(traders().with{product_D > 0})){
					if(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}.size() == 0){
						stock_D += product_D
						maximum_stock_size -= product_D
						sum_all_products -= product_D
						sum_product_D -= product_D
						product_D = 0
					}
					else{
						def buyer = maxOneOf(linkNeighbors().with{demand > 0 || maximum_stock_size > 0}){
							price
						}
						if(buyer.price >= price){
							product_D--
							sum_all_products--
							sum_product_D--
							ask(buyer){
								if(demand == 0){
									stock_D += 1
									maximum_stock_size -= 1
								}
								else{
									demand--
									ask(on_site){
										volume_D++
									}
								}
							}
						}
						else{
							stock_D += product_D
							maximum_stock_size -= product_D
							sum_all_products -= product_D
							sum_product_D -= product_D
							product_D = 0
						}
					}
				}
			}
		}
	}
	
	////////////////////REPORTERS////////////////////
	
	def getNumTotalLinks(){
		return count(links())
	}
	
	def getAverageDegree(){
		int total_links = 0
		ask(traders()){
			total_links = total_links + count(linkNeighbors())
		}
		float average_degree = total_links / count(traders())
		return average_degree
	}
	
	//This procedure was copied from the original Brughmans and Poblome model directly.
	def getClusteringCoefficient(){
		def clustering_coefficient2 = 0
				
		if(allQ(traders().with{linkNeighbors().size() > 0}){linkNeighbors().size() <= 1} == true){
			return clustering_coefficient2
		}
		else{
			int num_qualifying_nodes = 0
			def sum_clust_coeff = 0
			ask(traders().with{linkNeighbors().size() > 0}){
				if(linkNeighbors().size() > 1){
					def t_clust_coeff = 0
					def link_neighbors = linkNeighbors()
					def in_neighborhood = []
					ask(links()){
						if (link_neighbors.contains(getEnd1()) &&
							link_neighbors.contains(getEnd2()) ){
							in_neighborhood.add(it)
						}
					}
					t_clust_coeff = (2 * in_neighborhood.size() / (linkNeighbors().size() * (linkNeighbors().size() - 1)))
					sum_clust_coeff += t_clust_coeff
					num_qualifying_nodes++
				}
			}
			clustering_coefficient2 = sum_clust_coeff / num_qualifying_nodes
			return clustering_coefficient2
		}
	}	
	
	def getAverageShortestPathDistance(){
		ShortestPath sp = new ShortestPath(network)
		double sum_path_length = 0
		int paths = 0
		for(source in traders()){
			for(target in source.other(traders())){
				double path_length = sp.getPathLength(source,target)
				if(Double.isFinite(path_length)){
					sum_path_length += path_length
					paths++
				}
			}
		}
		def AverageShortestPathDistance = sum_path_length/paths
		return AverageShortestPathDistance
	}
	
	def getNumSites1(){
		def total_A = []
		def total_B = []
		def total_C = []
		def total_D = []
		
		ask(sites()){
			if(volume_A > 0){
				total_A.add(it)
			}
			if(volume_B > 0){
				total_B.add(it)
			}
			if(volume_C > 0){
				total_C.add(it)
			}
			if(volume_D > 0){
				total_D.add(it)
			}
		}
	
		def list = [total_A, total_B, total_C, total_D]
		def sorted_list = list.sort{a -> a.size()}
		return item(3, sorted_list).size()
	}
		
	def getNumSites2(){
		def total_A = []
		def total_B = []
		def total_C = []
		def total_D = []
		
		ask(sites()){
			if(volume_A > 0){
				total_A.add(it)
			}
			if(volume_B > 0){
				total_B.add(it)
			}
			if(volume_C > 0){
				total_C.add(it)
			}
			if(volume_D > 0){
				total_D.add(it)
			}
		}
	
		def list = [total_A, total_B, total_C, total_D]
		def sorted_list = list.sort{a -> a.size()}
		return item(2, sorted_list).size()
	}
		
	def getNumSites3(){
		def total_A = []
		def total_B = []
		def total_C = []
		def total_D = []
		
		ask(sites()){
			if(volume_A > 0){
				total_A.add(it)
			}
			if(volume_B > 0){
				total_B.add(it)
			}
			if(volume_C > 0){
				total_C.add(it)
			}
			if(volume_D > 0){
				total_D.add(it)
			}
		}
	
		def list = [total_A, total_B, total_C, total_D]
		def sorted_list = list.sort{a -> a.size()}
		return item(1, sorted_list).size()
	}
		
	def getNumSites4(){
		def total_A = []
		def total_B = []
		def total_C = []
		def total_D = []
		
		ask(sites()){
			if(volume_A > 0){
				total_A.add(it)
			}
			if(volume_B > 0){
				total_B.add(it)
			}
			if(volume_C > 0){
				total_C.add(it)
			}
			if(volume_D > 0){
				total_D.add(it)
			}
		}
	
		def list = [total_A, total_B, total_C, total_D]
		def sorted_list = list.sort{a -> a.size()}
		return item(0, sorted_list).size()
	}
	
	def getClosenessCentrality(){
		ClosenessCentrality closeness = new ClosenessCentrality(network.getGraph());
		for(i in traders()) {
			i.closeness = closeness.getVertexScore(i)
		}
	}
	
	def getBetweennessCentrality(){
		BetweennessCentrality ranker = new BetweennessCentrality(network.getGraph());
		ranker.setRemoveRankScoresOnFinalize(false);
		ranker.evaluate();
		for(i in traders()) {
			i.betweenness = ranker.getVertexRankScore(i)
		}
	}
	
	/*For the sensitivity analysis, a list of site data is created.
	(It is called a dict, cause it is converted to a dictionary in the python script we used to analyse the data.)
	The site dict contains the following for each site:
	- the site number
	- whether the site is a production site and, if so, of which product
	- the volume of each product on the site
	- a list of the closeness and betweenness centralities of each trader on the site
	 */
	def getSiteData(){
		def site_dict = []
		for(i in sites()){
			def centralitylist = []
			for(o in i.trader_list) {
				centralitylist.add([o.closeness, o.betweenness])
			}
			def producer = '"N"'
			if(i.producer_A == true) {
				producer = '"A"'
			}
			if(i.producer_B == true) {
				producer = '"B"'
			}
			if(i.producer_C == true) {
				producer = '"C"'
			}
			if(i.producer_D == true) {
				producer = '"D"'
			}
			site_dict.add([i.getWho(), [producer, i.volume_A, i.volume_B, i.volume_C, i.volume_D, centralitylist]])
		}
		return site_dict
	}
	
	//Output all the data
	def outputDataParameterSweep() {
		Parameters p = RunEnvironment.getInstance().getParameters()
		int ticks = ticks()
		def random_seed = p.getValue("randomSeed")
		getBetweennessCentrality()
		getClosenessCentrality()
		def site_dict = getSiteData()
		def ln = System.getProperty('line.separator')
		
		//The following line must be altered to refer to the outputParamaterSweep.csv
		def outputFile = new File('PATH\\TO\\REPAST\\WORKSPACE\\MERCURY\\output\\outputParameterSweep.csv')
		outputFile.append(ln + random_seed + ";" + ticks + ";" + equal_traders_production_site + ";" + traders_distribution + ";" + network_structure + ";" + proportion_inter_site_links + ";" + proportion_intra_site_links + ";" + proportion_mutual_neighbors + ";" +  traders_production_site + ";" + one_production_site_dominance + ";" + maximum_degree + ";" + local_knowledge +  ";" + maximum_demand + ";" + circle_link_counter + ";" + inter_link_counter + ";" + intra_link_counter + ";" + mutual_link_counter + ";" + connect_components_link_counter + ";" + average_degree + ";" + clustering_coefficient + ";" + average_shortest_path_distance + ";" + site_dict)
		return "Output written"
	}
}