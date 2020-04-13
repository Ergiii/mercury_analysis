package mercury;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoObserver extends BaseObserver{

	/**
	 * Makes a number of randomly oriented sites and then executes a set of commands on the
	 * created sites.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> createSites(int number, Closure closure) {
		AgentSet<mercury.relogo.Site> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"Site");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.Site){
				result.add((mercury.relogo.Site)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented sites and then executes a set of commands on the
	 * created sites.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> createSites(int number) {
		return createSites(number,null);
	}

	/**
	 * Makes a number of uniformly fanned sites and then executes a set of commands on the
	 * created sites.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> createOrderedSites(int number, Closure closure) {
		AgentSet<mercury.relogo.Site> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"Site");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.Site){
				result.add((mercury.relogo.Site)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned sites and then executes a set of commands on the
	 * created sites.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> createOrderedSites(int number) {
		return createOrderedSites(number,null);
	}

	/**
	 * Queries if object is a site.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a site
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public boolean isSiteQ(Object o){
		return (o instanceof mercury.relogo.Site);
	}

	/**
	 * Returns an agentset containing all sites.
	 * 
	 * @return agentset of all sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> sites(){
		AgentSet<mercury.relogo.Site> a = new AgentSet<mercury.relogo.Site>();
		for (Object e : this.getContext().getObjects(mercury.relogo.Site.class)) {
			if (e instanceof mercury.relogo.Site){
				a.add((mercury.relogo.Site)e);
			}
		}
		return a;
	}

	/**
	 * Returns the site with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public mercury.relogo.Site site(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof mercury.relogo.Site)
			return (mercury.relogo.Site) turtle;
		return null;
	}

	/**
	 * Returns an agentset of sites on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of sites on patch p
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> sitesOn(Patch p){
		AgentSet<mercury.relogo.Site> result = new AgentSet<mercury.relogo.Site>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"site")){
			if (t instanceof mercury.relogo.Site)
			result.add((mercury.relogo.Site)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of sites on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of sites on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> sitesOn(Turtle t){
		AgentSet<mercury.relogo.Site> result = new AgentSet<mercury.relogo.Site>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"site")){
			if (tt instanceof mercury.relogo.Site)
			result.add((mercury.relogo.Site)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of sites on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of sites on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> sitesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<mercury.relogo.Site>();
		}

		Set<mercury.relogo.Site> total = new HashSet<mercury.relogo.Site>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(sitesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(sitesOn(p));
				}
			}
		}
		return new AgentSet<mercury.relogo.Site>(total);
	}

	/**
	 * Makes a number of randomly oriented traders and then executes a set of commands on the
	 * created traders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> createTraders(int number, Closure closure) {
		AgentSet<mercury.relogo.Trader> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"Trader");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.Trader){
				result.add((mercury.relogo.Trader)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented traders and then executes a set of commands on the
	 * created traders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> createTraders(int number) {
		return createTraders(number,null);
	}

	/**
	 * Makes a number of uniformly fanned traders and then executes a set of commands on the
	 * created traders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> createOrderedTraders(int number, Closure closure) {
		AgentSet<mercury.relogo.Trader> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"Trader");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.Trader){
				result.add((mercury.relogo.Trader)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned traders and then executes a set of commands on the
	 * created traders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> createOrderedTraders(int number) {
		return createOrderedTraders(number,null);
	}

	/**
	 * Queries if object is a trader.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a trader
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public boolean isTraderQ(Object o){
		return (o instanceof mercury.relogo.Trader);
	}

	/**
	 * Returns an agentset containing all traders.
	 * 
	 * @return agentset of all traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> traders(){
		AgentSet<mercury.relogo.Trader> a = new AgentSet<mercury.relogo.Trader>();
		for (Object e : this.getContext().getObjects(mercury.relogo.Trader.class)) {
			if (e instanceof mercury.relogo.Trader){
				a.add((mercury.relogo.Trader)e);
			}
		}
		return a;
	}

	/**
	 * Returns the trader with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public mercury.relogo.Trader trader(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof mercury.relogo.Trader)
			return (mercury.relogo.Trader) turtle;
		return null;
	}

	/**
	 * Returns an agentset of traders on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of traders on patch p
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> tradersOn(Patch p){
		AgentSet<mercury.relogo.Trader> result = new AgentSet<mercury.relogo.Trader>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"trader")){
			if (t instanceof mercury.relogo.Trader)
			result.add((mercury.relogo.Trader)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of traders on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of traders on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> tradersOn(Turtle t){
		AgentSet<mercury.relogo.Trader> result = new AgentSet<mercury.relogo.Trader>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"trader")){
			if (tt instanceof mercury.relogo.Trader)
			result.add((mercury.relogo.Trader)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of traders on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of traders on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> tradersOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<mercury.relogo.Trader>();
		}

		Set<mercury.relogo.Trader> total = new HashSet<mercury.relogo.Trader>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(tradersOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(tradersOn(p));
				}
			}
		}
		return new AgentSet<mercury.relogo.Trader>(total);
	}

	/**
	 * Makes a number of randomly oriented userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> createUserTurtles(int number, Closure closure) {
		AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.crt(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.UserTurtle){
				result.add((mercury.relogo.UserTurtle)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of randomly oriented userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> createUserTurtles(int number) {
		return createUserTurtles(number,null);
	}

	/**
	 * Makes a number of uniformly fanned userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> createOrderedUserTurtles(int number, Closure closure) {
		AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.cro(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.UserTurtle){
				result.add((mercury.relogo.UserTurtle)t);
			}
		} 
		return result; 
	}

	/**
	 * Makes a number of uniformly fanned userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> createOrderedUserTurtles(int number) {
		return createOrderedUserTurtles(number,null);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof mercury.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> userTurtles(){
		AgentSet<mercury.relogo.UserTurtle> a = new AgentSet<mercury.relogo.UserTurtle>();
		for (Object e : this.getContext().getObjects(mercury.relogo.UserTurtle.class)) {
			if (e instanceof mercury.relogo.UserTurtle){
				a.add((mercury.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public mercury.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), this);
		if (turtle instanceof mercury.relogo.UserTurtle)
			return (mercury.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<mercury.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),this,"userTurtle")){
			if (t instanceof mercury.relogo.UserTurtle)
			result.add((mercury.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<mercury.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),this,"userTurtle")){
			if (tt instanceof mercury.relogo.UserTurtle)
			result.add((mercury.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<mercury.relogo.UserTurtle>();
		}

		Set<mercury.relogo.UserTurtle> total = new HashSet<mercury.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<mercury.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a siteLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a siteLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public boolean isSiteLinkQ(Object o){
		return (o instanceof mercury.relogo.SiteLink);
	}

	/**
	 * Returns an agentset containing all siteLinks.
	 * 
	 * @return agentset of all siteLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> siteLinks(){
		AgentSet<mercury.relogo.SiteLink> a = new AgentSet<mercury.relogo.SiteLink>();
		for (Object e : this.getContext().getObjects(mercury.relogo.SiteLink.class)) {
			if (e instanceof mercury.relogo.SiteLink){
				a.add((mercury.relogo.SiteLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the siteLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return siteLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink siteLink(Number oneEnd, Number otherEnd) {
		return (mercury.relogo.SiteLink)(this.getNetwork("SiteLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the siteLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return siteLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink siteLink(Turtle oneEnd, Turtle otherEnd) {
		return siteLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof mercury.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public AgentSet<mercury.relogo.UserLink> userLinks(){
		AgentSet<mercury.relogo.UserLink> a = new AgentSet<mercury.relogo.UserLink>();
		for (Object e : this.getContext().getObjects(mercury.relogo.UserLink.class)) {
			if (e instanceof mercury.relogo.UserLink){
				a.add((mercury.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public mercury.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (mercury.relogo.UserLink)(this.getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public mercury.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Returns the value of the global variable num_traders.
	 *
	 * @return the value of the global variable num_traders
	 */
	@ReLogoBuilderGeneratedFor("global: num_traders")
	public Object getNum_traders(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("num_traders");
	}

	/**
	 * Sets the value of the global variable num_traders.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: num_traders")
	public void setNum_traders(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("num_traders",value);
	}

	/**
	 * Returns the value of the global variable num_sites.
	 *
	 * @return the value of the global variable num_sites
	 */
	@ReLogoBuilderGeneratedFor("global: num_sites")
	public Object getNum_sites(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("num_sites");
	}

	/**
	 * Sets the value of the global variable num_sites.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: num_sites")
	public void setNum_sites(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("num_sites",value);
	}

	/**
	 * Returns the value of the global variable proportion_mutual_neighbors.
	 *
	 * @return the value of the global variable proportion_mutual_neighbors
	 */
	@ReLogoBuilderGeneratedFor("global: proportion_mutual_neighbors")
	public Object getProportion_mutual_neighbors(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("proportion_mutual_neighbors");
	}

	/**
	 * Sets the value of the global variable proportion_mutual_neighbors.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: proportion_mutual_neighbors")
	public void setProportion_mutual_neighbors(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("proportion_mutual_neighbors",value);
	}

	/**
	 * Returns the value of the global variable equal_traders_production_site.
	 *
	 * @return the value of the global variable equal_traders_production_site
	 */
	@ReLogoBuilderGeneratedFor("global: equal_traders_production_site")
	public Object getEqual_traders_production_site(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("equal_traders_production_site");
	}

	/**
	 * Sets the value of the global variable equal_traders_production_site.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: equal_traders_production_site")
	public void setEqual_traders_production_site(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("equal_traders_production_site",value);
	}

	/**
	 * Returns the value of the global variable traders_distribution.
	 *
	 * @return the value of the global variable traders_distribution
	 */
	@ReLogoBuilderGeneratedFor("global: traders_distribution")
	public Object getTraders_distribution(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("traders_distribution");
	}

	/**
	 * Sets the value of the global variable traders_distribution.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: traders_distribution")
	public void setTraders_distribution(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("traders_distribution",value);
	}

	/**
	 * Returns the value of the global variable traders_production_site.
	 *
	 * @return the value of the global variable traders_production_site
	 */
	@ReLogoBuilderGeneratedFor("global: traders_production_site")
	public Object getTraders_production_site(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("traders_production_site");
	}

	/**
	 * Sets the value of the global variable traders_production_site.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: traders_production_site")
	public void setTraders_production_site(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("traders_production_site",value);
	}

	/**
	 * Returns the value of the global variable network_structure.
	 *
	 * @return the value of the global variable network_structure
	 */
	@ReLogoBuilderGeneratedFor("global: network_structure")
	public Object getNetwork_structure(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("network_structure");
	}

	/**
	 * Sets the value of the global variable network_structure.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: network_structure")
	public void setNetwork_structure(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("network_structure",value);
	}

	/**
	 * Returns the value of the global variable maximum_degree.
	 *
	 * @return the value of the global variable maximum_degree
	 */
	@ReLogoBuilderGeneratedFor("global: maximum_degree")
	public Object getMaximum_degree(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("maximum_degree");
	}

	/**
	 * Sets the value of the global variable maximum_degree.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: maximum_degree")
	public void setMaximum_degree(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("maximum_degree",value);
	}

	/**
	 * Returns the value of the global variable proportion_inter_site_links.
	 *
	 * @return the value of the global variable proportion_inter_site_links
	 */
	@ReLogoBuilderGeneratedFor("global: proportion_inter_site_links")
	public Object getProportion_inter_site_links(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("proportion_inter_site_links");
	}

	/**
	 * Sets the value of the global variable proportion_inter_site_links.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: proportion_inter_site_links")
	public void setProportion_inter_site_links(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("proportion_inter_site_links",value);
	}

	/**
	 * Returns the value of the global variable proportion_intra_site_links.
	 *
	 * @return the value of the global variable proportion_intra_site_links
	 */
	@ReLogoBuilderGeneratedFor("global: proportion_intra_site_links")
	public Object getProportion_intra_site_links(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("proportion_intra_site_links");
	}

	/**
	 * Sets the value of the global variable proportion_intra_site_links.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: proportion_intra_site_links")
	public void setProportion_intra_site_links(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("proportion_intra_site_links",value);
	}

	/**
	 * Returns the value of the global variable maximum_demand.
	 *
	 * @return the value of the global variable maximum_demand
	 */
	@ReLogoBuilderGeneratedFor("global: maximum_demand")
	public Object getMaximum_demand(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("maximum_demand");
	}

	/**
	 * Sets the value of the global variable maximum_demand.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: maximum_demand")
	public void setMaximum_demand(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("maximum_demand",value);
	}

	/**
	 * Returns the value of the global variable local_knowledge.
	 *
	 * @return the value of the global variable local_knowledge
	 */
	@ReLogoBuilderGeneratedFor("global: local_knowledge")
	public Object getLocal_knowledge(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("local_knowledge");
	}

	/**
	 * Sets the value of the global variable local_knowledge.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: local_knowledge")
	public void setLocal_knowledge(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("local_knowledge",value);
	}

	/**
	 * Returns the value of the global variable one_production_site_dominance.
	 *
	 * @return the value of the global variable one_production_site_dominance
	 */
	@ReLogoBuilderGeneratedFor("global: one_production_site_dominance")
	public Object getOne_production_site_dominance(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("one_production_site_dominance");
	}

	/**
	 * Sets the value of the global variable one_production_site_dominance.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: one_production_site_dominance")
	public void setOne_production_site_dominance(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("one_production_site_dominance",value);
	}


}