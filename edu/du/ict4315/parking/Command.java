package edu.du.ict4315.parking;

public interface Command {

	public String getCommandName();
	
	public String getDisplayName();
	
	public String execute(Properties params);
	
	public boolean checkParameters(Properties params);
}
