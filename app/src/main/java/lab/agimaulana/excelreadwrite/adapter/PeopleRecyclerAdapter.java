package lab.agimaulana.excelreadwrite.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lab.agimaulana.excelreadwrite.R;
import lab.agimaulana.excelreadwrite.model.People;

/**
 * Created by root on 8/13/16.
 */
public class PeopleRecyclerAdapter extends RecyclerView.Adapter<PeopleRecyclerAdapter.PeopleHolder>{
    private List<People> peoples;

    public PeopleRecyclerAdapter() {
        peoples = new ArrayList<>();
    }

    public PeopleRecyclerAdapter(List<People> peoples) {
        this.peoples = peoples;
    }

    public void add(People people){
        peoples.add(people);
    }

    public List<People> getPeoples(){
        return peoples;
    }

    @Override
    public PeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new PeopleHolder(v);
    }

    @Override
    public void onBindViewHolder(PeopleHolder holder, int position) {
        People people = peoples.get(position);
        holder.tvName.setText(people.getName());
        holder.tvJob.setText(people.getJob());
        holder.tvPhone.setText(people.getPhone());
        holder.tvEmail.setText(people.getEmail());
        holder.tvAddress.setText(people.getAddress());
    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }

    static class PeopleHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tvName, tvJob, tvPhone, tvEmail, tvAddress;

        public PeopleHolder(View itemView) {
            super(itemView);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.textview_name);
            tvJob = (AppCompatTextView) itemView.findViewById(R.id.textview_job);
            tvPhone = (AppCompatTextView) itemView.findViewById(R.id.textview_phone);
            tvEmail = (AppCompatTextView) itemView.findViewById(R.id.textview_email);
            tvAddress = (AppCompatTextView) itemView.findViewById(R.id.textview_address);
        }
    }
}
