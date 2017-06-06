package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScratchPadManager {
	private ObservableList<String> notes;
	
	public ScratchPadManager(){
		notes = FXCollections.observableArrayList();
	}
	
	protected ObservableList<String> getNotes() {
		return notes; 
	}
	
	public void deleteNote(String a)
	{
		notes.remove(a);
	}

	public int numNotes()
	{
		return notes.size();
	}

	public String getNote(int index)
	{
		return notes.get(index);
	}	
	
	public ObservableList<String> addNote(String note){
		notes.add(note);
		return notes;
	}
}
