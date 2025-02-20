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
import repast.simphony.space.SpatialException;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoTurtle extends BaseTurtle{

	/**
	 * Makes a number of new sites and then executes a set of commands on the
	 * created sites.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> hatchSites(int number, Closure closure) {
		AgentSet<mercury.relogo.Site> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Site");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.Site){
				result.add((mercury.relogo.Site)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new sites and then executes a set of commands on the
	 * created sites.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created sites
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> hatchSites(int number) {
		return hatchSites(number,null);
	}

	/**
	 * Returns an agentset of sites from the patch of the caller.
	 * 
	 * @return agentset of sites from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> sitesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<mercury.relogo.Site> result = new AgentSet<mercury.relogo.Site>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"site")){
			if (t instanceof mercury.relogo.Site)
			result.add((mercury.relogo.Site)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of sites on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of sites at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Site")
	public AgentSet<mercury.relogo.Site> sitesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<mercury.relogo.Site> result = new AgentSet<mercury.relogo.Site>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"site")){
			if (t instanceof mercury.relogo.Site)
			result.add((mercury.relogo.Site)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<mercury.relogo.Site>();
		}
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
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"site")){
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
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"site")){
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
		for (Object e : this.getMyObserver().getContext().getObjects(mercury.relogo.Site.class)) {
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
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof mercury.relogo.Site)
			return (mercury.relogo.Site) turtle;
		return null;
	}

	/**
	 * Makes a number of new traders and then executes a set of commands on the
	 * created traders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> hatchTraders(int number, Closure closure) {
		AgentSet<mercury.relogo.Trader> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Trader");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.Trader){
				result.add((mercury.relogo.Trader)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new traders and then executes a set of commands on the
	 * created traders.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created traders
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> hatchTraders(int number) {
		return hatchTraders(number,null);
	}

	/**
	 * Returns an agentset of traders from the patch of the caller.
	 * 
	 * @return agentset of traders from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> tradersHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<mercury.relogo.Trader> result = new AgentSet<mercury.relogo.Trader>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"trader")){
			if (t instanceof mercury.relogo.Trader)
			result.add((mercury.relogo.Trader)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of traders on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of traders at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.Trader")
	public AgentSet<mercury.relogo.Trader> tradersAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<mercury.relogo.Trader> result = new AgentSet<mercury.relogo.Trader>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"trader")){
			if (t instanceof mercury.relogo.Trader)
			result.add((mercury.relogo.Trader)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<mercury.relogo.Trader>();
		}
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
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"trader")){
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
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"trader")){
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
		for (Object e : this.getMyObserver().getContext().getObjects(mercury.relogo.Trader.class)) {
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
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof mercury.relogo.Trader)
			return (mercury.relogo.Trader) turtle;
		return null;
	}

	/**
	 * Makes a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> hatchUserTurtles(int number, Closure closure) {
		AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof mercury.relogo.UserTurtle){
				result.add((mercury.relogo.UserTurtle)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> hatchUserTurtles(int number) {
		return hatchUserTurtles(number,null);
	}

	/**
	 * Returns an agentset of userTurtles from the patch of the caller.
	 * 
	 * @return agentset of userTurtles from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> userTurtlesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<mercury.relogo.UserTurtle>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof mercury.relogo.UserTurtle)
			result.add((mercury.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of userTurtles on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of userTurtles at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserTurtle")
	public AgentSet<mercury.relogo.UserTurtle> userTurtlesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<mercury.relogo.UserTurtle> result = new AgentSet<mercury.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof mercury.relogo.UserTurtle)
			result.add((mercury.relogo.UserTurtle)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<mercury.relogo.UserTurtle>();
		}
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
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"userTurtle")){
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
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"userTurtle")){
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
		for (Object e : this.getMyObserver().getContext().getObjects(mercury.relogo.UserTurtle.class)) {
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
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof mercury.relogo.UserTurtle)
			return (mercury.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Makes a directed siteLink from a turtle to the caller then executes a set of
	 * commands on the created siteLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created siteLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink createSiteLinkFrom(Turtle t, Closure closure){
		mercury.relogo.SiteLink link = (mercury.relogo.SiteLink)this.getMyObserver().getNetwork("SiteLink").addEdge(t,this);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes a directed siteLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return created siteLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink createSiteLinkFrom(Turtle t){
			return createSiteLinkFrom(t,null);
	}

	/**
	 * Makes directed siteLinks from a collection to the caller then executes a set
	 * of commands on the created siteLinks.
	 * 
	 * @param a
	 *            a collection
	 * @param closure
	 *            a set of commands
	 * @return created siteLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> createSiteLinksFrom(Collection<? extends Turtle> a, Closure closure){
		AgentSet<mercury.relogo.SiteLink> links = new AgentSet<mercury.relogo.SiteLink>();
		for(Turtle t : a){
			links.add((mercury.relogo.SiteLink)this.getMyObserver().getNetwork("SiteLink").addEdge(t,this));
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes directed siteLinks from a collection to the caller.
	 * 
	 * @param a
	 *            a collection
	 * @return created siteLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> createSiteLinksFrom(Collection<? extends Turtle> a){
		return createSiteLinksFrom(a,null);
	}

	/**
	 * Makes a directed siteLink to a turtle from the caller then executes a set of
	 * commands on the created siteLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created siteLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink createSiteLinkTo(Turtle t, Closure closure){
		mercury.relogo.SiteLink link = (mercury.relogo.SiteLink)this.getMyObserver().getNetwork("SiteLink").addEdge(this,t);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes a directed siteLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return created siteLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink createSiteLinkTo(Turtle t){
			return createSiteLinkTo(t,null);
	}

	/**
	 * Makes directed siteLinks to a collection from the caller then executes a set
	 * of commands on the created siteLinks.
	 * 
	 * @param a
	 *            a collection
	 * @param closure
	 *            a set of commands
	 * @return created siteLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> createSiteLinksTo(Collection<? extends Turtle> a, Closure closure){
		AgentSet<mercury.relogo.SiteLink> links = new AgentSet<mercury.relogo.SiteLink>();
		for(Object t : a){
			if (t instanceof Turtle){
				links.add((mercury.relogo.SiteLink)this.getMyObserver().getNetwork("SiteLink").addEdge(this,(Turtle)t));
			}
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes directed siteLinks to a collection from the caller.
	 * 
	 * @param a
	 *            a collection
	 * @return created siteLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> createSiteLinksTo(Collection<? extends Turtle> a){
		return createSiteLinksTo(a,null);
	}

	/**
	 * Queries if there is a directed siteLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return true or false based on whether there is a directed siteLink from
	 *         turtle t to the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public boolean inSiteLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("SiteLink").isPredecessor(t, this);
	}

	/**
	 * Returns the agentset with directed siteLinks to the caller.
	 * 
	 * @return agentset with directed siteLinks to the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet inSiteLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("SiteLink").getPredecessors(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the directed siteLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return directed siteLink from turtle t to the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink inSiteLinkFrom(Turtle t){
		return (mercury.relogo.SiteLink)this.getMyObserver().getNetwork("SiteLink").getEdge(t,this);
	}

	/**
	 * Returns an agentset of directed siteLinks from other turtles to the caller.
	 * 
	 * @return agentset of directed siteLinks from other turtles to the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> myInSiteLinks(){
		AgentSet<mercury.relogo.SiteLink> result = new AgentSet<mercury.relogo.SiteLink>();
		for(Object o :  this.getMyObserver().getNetwork("SiteLink").getInEdges(this)){
			if (o instanceof mercury.relogo.SiteLink){
				result.add((mercury.relogo.SiteLink) o);
			}
		}
		return result;
	}

	/**
	 * Returns an agentset of directed siteLinks to other turtles from the caller.
	 * 
	 * @return agentset of directed siteLinks to other turtles from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> myOutSiteLinks(){
		AgentSet<mercury.relogo.SiteLink> result = new AgentSet<mercury.relogo.SiteLink>();
		for(Object o :  this.getMyObserver().getNetwork("SiteLink").getOutEdges(this)){
			if (o instanceof mercury.relogo.SiteLink){
				result.add((mercury.relogo.SiteLink) o);
			}
		}
		return result;
	}

	/**
	 * Queries if there is a directed siteLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return true or false based on whether there is a directed siteLink to
	 *         turtle t from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public boolean outSiteLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("SiteLink").isPredecessor(this, t);
	}

	/**
	 * Returns the agentset with directed siteLinks from the caller.
	 * 
	 * @return agentset with directed siteLinks from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet outSiteLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("SiteLink").getSuccessors(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the directed siteLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return directed siteLink to turtle t from the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public mercury.relogo.SiteLink outSiteLinkTo(Turtle t){
		return (mercury.relogo.SiteLink)this.getMyObserver().getNetwork("SiteLink").getEdge(this, t);
	}

	/**
	 * Reports true if there is a siteLink connecting t and the caller.
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public boolean siteLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("SiteLink").isAdjacent(this, t);
	}

	/**
	 * Returns the agentset of all turtles found at the other end of
	 * siteLinks connected to the calling turtle.
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet siteLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("SiteLink").getAdjacent(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns an agentset of the caller's siteLinks.
	 * 
	 * @return agentset of the caller's siteLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.SiteLink")
	public AgentSet<mercury.relogo.SiteLink> mySiteLinks(){
		AgentSet<mercury.relogo.SiteLink> result = new AgentSet<mercury.relogo.SiteLink>();
		for(Object o : this.getMyObserver().getNetwork("SiteLink").getEdges(this)){
			if (o instanceof mercury.relogo.SiteLink){
				result.add((mercury.relogo.SiteLink)o);
			}
		}
		return result;
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
		for (Object e : this.getMyObserver().getContext().getObjects(mercury.relogo.SiteLink.class)) {
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
		return (mercury.relogo.SiteLink)(this.getMyObserver().getNetwork("SiteLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
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
	 * Makes an undirected userLink between the caller and a turtle then executes a
	 * set of commands on the created userLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public mercury.relogo.UserLink createUserLinkWith(Turtle t, Closure closure){
		mercury.relogo.UserLink link = (mercury.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(this,t);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes an undirected userLink between the caller and a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public mercury.relogo.UserLink createUserLinkWith(Turtle t){
			return createUserLinkWith(t,null);
	}

	/**
	 * Makes undirected userLinks between the caller and a collection of turtles
	 * then executes a set of commands on the created userLinks.
	 * 
	 * @param a
	 *            a collection of turtles
	 * @param closure
	 *            a set of commands
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public AgentSet<mercury.relogo.UserLink> createUserLinksWith(Collection<? extends Turtle> a, Closure closure){
		AgentSet<mercury.relogo.UserLink> links = new AgentSet<mercury.relogo.UserLink>();
		for(Object t : a){
			if (t instanceof Turtle){
				links.add((mercury.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(this,(Turtle)t));
			}
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes undirected userLinks between the caller and a collection of turtles.
	 * 
	 * @param a
	 *            an agentset
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public AgentSet<mercury.relogo.UserLink> createUserLinksWith(Collection<? extends Turtle> a){
		return createUserLinksWith(a,null);
	}

	/**
	 * Reports true if there is a userLink connecting t and the caller.
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public boolean userLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isAdjacent(this, t);
	}

	/**
	 * Returns the agentset of all turtles found at the other end of
	 * userLinks connected to the calling turtle.
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public AgentSet userLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getAdjacent(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the undirected userLink between a turtle and the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return undirected userLink between turtle t and the caller
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public mercury.relogo.UserLink userLinkWith(Turtle t){
		return (mercury.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").getEdge(this,t);
	}

	/**
	 * Returns an agentset of the caller's userLinks.
	 * 
	 * @return agentset of the caller's userLinks
	 */
	@ReLogoBuilderGeneratedFor("mercury.relogo.UserLink")
	public AgentSet<mercury.relogo.UserLink> myUserLinks(){
		AgentSet<mercury.relogo.UserLink> result = new AgentSet<mercury.relogo.UserLink>();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getEdges(this)){
			if (o instanceof mercury.relogo.UserLink){
				result.add((mercury.relogo.UserLink)o);
			}
		}
		return result;
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
		for (Object e : this.getMyObserver().getContext().getObjects(mercury.relogo.UserLink.class)) {
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
		return (mercury.relogo.UserLink)(this.getMyObserver().getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
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