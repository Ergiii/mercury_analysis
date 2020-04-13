package mercury.relogo

import repast.simphony.parameter.Parameters;
import repast.simphony.relogo.factories.AbstractReLogoGlobalsAndPanelFactory;
import repast.simphony.engine.environment.RunEnvironment;

public class UserGlobalsAndPanelFactory extends AbstractReLogoGlobalsAndPanelFactory{
	public void addGlobalsAndPanelComponents(){
		
		Parameters p = RunEnvironment.getInstance().getParameters()
		
		//Global variables
		addGlobal("num_traders",1000)
		addGlobal("num_sites",100)
		addGlobal("proportion_mutual_neighbors",2)
		addGlobal("equal_traders_production_site", p.getBoolean("equal_traders_production_site"))
		addGlobal("traders_distribution", p.getString("traders_distribution"))
		addGlobal("traders_production_site", p.getInteger("traders_production_site"))
		addGlobal("network_structure", p.getString("network_structure"))
		addGlobal("maximum_degree", p.getInteger("maximum_degree"))
		addGlobal("proportion_inter_site_links", p.getDouble("proportion_inter_site_links"))
		addGlobal("proportion_intra_site_links", p.getDouble("proportion_intra_site_links"))
		addGlobal("maximum_demand", p.getInteger("max_demand"))
		addGlobal("local_knowledge", p.getDouble("local_knowledge"))
		addGlobal("one_production_site_dominance", p.getInteger("one_production_site_dominance"))
	}
}