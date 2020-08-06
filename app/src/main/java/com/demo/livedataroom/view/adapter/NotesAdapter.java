package com.demo.livedataroom.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.livedataroom.R;
import com.demo.livedataroom.model.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends ListAdapter<Note,NotesAdapter.NoteViewHolder> {

    private onItemClickListener onItemClickListener;
    public NotesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static  final DiffUtil.ItemCallback<Note>DIFF_CALLBACK=new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return  oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDescription().equals(newItem.getDescription())&&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote=getItem(position);
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
    }

/*
    public void setNotes(List<Note>notes){
        this.notes=notes;
        notifyDataSetChanged();
    }
*/

    public Note getNoteAt(int position){
        return getItem(position);
    }
    class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle,textViewDescription,textViewPriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.text_view_title);
            textViewDescription=itemView.findViewById(R.id.text_view_description);
            textViewPriority=itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(getItem((position)));
                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
