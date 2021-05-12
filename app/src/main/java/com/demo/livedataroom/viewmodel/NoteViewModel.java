package com.demo.livedataroom.viewmodel;

import android.app.Application;

import com.demo.livedataroom.repository.NoteRepository;
import com.demo.livedataroom.model.Note;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>>allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
        allNotes=noteRepository.getAllNotes();
    }

    public void insert(Note note){
        noteRepository.insert(note);
    }

    public void update(Note note){
        noteRepository.update(note);
    }

    public void Delete(Note note){
        noteRepository.delete(note);
    }

    public void DeleteAll(){
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
