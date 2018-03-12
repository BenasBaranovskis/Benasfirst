package k.benasfirst;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class TargetAdapter {




    public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private Context context;
        private LayoutInflater inflater;
        private List<Target> targets = Collections.emptyList();

    //private Pokemonas currentPokemon;

        public static final String ENTRY = "com.byethost12.kitm.mobiliaplikacija";

        //konstruktorius reikalingas susieti
        // esama langa ir perduoti sarasa pokemonui is DB
        public PokemonAdapter(Context context,List<Target> targets){
            this.context = context;
            this.targets = targets;
            inflater = LayoutInflater.from(context);
        }

        @Override
        //inflate the layout when viewholder is created
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.container_pok,parent,false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        //bind data
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //get current position of item in
            // recyclerview to bind data and assign value from list
            MyHolder myHolder = (MyHolder) holder;

            Target currentTarget = targets.get(position);
            myHolder.tvPavadinimas.setText(           currentTarget.getName());
            myHolder.tvTipas.setText("Car: "      + currentTarget.getCar());
            myHolder.tvGalia.setText("Hostility: "      + currentTarget.getHostility());
            myHolder.tvId.setText("ID: "   + currentTarget.getId());
            myHolder.tvSavybes.setText("To do: "  + currentTarget.getToDo());
        }

        @Override
        public int getItemCount() {
            return targets.size();
        }

        class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

       TextView tvPavadinimas,tvTipas,tvGalia,tvSavybes, tvId;

            public MyHolder(View itemView){
                super(itemView);
                            tvTargetname  = (TextView)itemView.findViewById(R.id.);
                            tvCar         = (TextView)itemView.findViewById(R.id.);
                            tvHostility         = (TextView)itemView.findViewById(R.id.);
                            tvToDo       = (TextView)itemView.findViewById(R.id.);
                            tvId            = (TextView)itemView.findViewById(R.id.id);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                int itemPosition = getAdapterPosition();
                int targetsID = targets.get(itemPosition).getId();

                // TODO: siųsti pasirinkto pokemono objektą vietoj id
                Target target = targets.get(itemPosition);

                Intent intent = new Intent(context, NewTargetActivity.class);

                intent.putExtra(ENTRY, targetsID);
                context.startActivity(intent);
            }


            }
        }
    }
}
