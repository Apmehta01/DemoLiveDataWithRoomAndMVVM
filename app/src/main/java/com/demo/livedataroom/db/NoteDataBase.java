package com.demo.livedataroom.db;

import android.content.Context;
import android.os.AsyncTask;

import com.demo.livedataroom.dao.NoteDao;
import com.demo.livedataroom.model.Note;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if(instance==null) {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public PopulateAsyncTask(NoteDataBase db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insertNote(new Note("Title 1","Discription 1",1));
            noteDao.insertNote(new Note("Title 2","Discription 2",2));
            noteDao.insertNote(new Note("Title 3","Discription 3",3));
            return null;
        }
    }
}
