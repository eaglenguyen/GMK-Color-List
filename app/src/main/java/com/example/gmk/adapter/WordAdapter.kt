package com.example.gmk.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.gmk.R
import com.example.gmk.WordListFragment
import java.util.*


class WordAdapter (position: Int, context: Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {


    private val filteredWord: List<String>



    private var wordStringResourceIds = intArrayOf(
        R.array.black,
        R.array.blue,
        R.array.red,
        R.array.white,
    )
    /**
     * Java style
     *
     * int[] wordStringResourceIds = {
    R.array.black,
    R.array.blue,

    };

    Random random = new Random();
    int randomIndex = random.nextInt(cardStringResourceIds.length);

    String[] cards = getResources().getStringArray(cardStringResourceIds[]);
     */
   // var random: Random? = Random()
   // var randomIndex = random!!.nextInt(cardStringResourceIds.length)

   // var cards: Array<String> = getResources().getStringArray(cardStringResourceIds.get(randomIndex))

   // var cards: Array<String> = getResources().getStringArray(cardStringResourceIds.get(postion))



    init {
        val wordsNew = context.resources.getStringArray(wordStringResourceIds[position]).toList()
        filteredWord = wordsNew.sorted()
    }



    // holds the view (button)
    class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val buttonView: Button = view.findViewById(R.id.button_item)
    }

    // inflates/displays the view( button )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_view, parent, false)


        return WordViewHolder(adapterLayout)


    }
    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWord[position]





        holder.buttonView.text = item


        /**
         *
         * Logic for url link
         */


        holder.buttonView.setOnClickListener {
            val context = holder.view.context
            val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}${item}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }


        // holder.buttonView.text = context.resources.getString(item.stringResourceId)
    }

    override fun getItemCount(): Int = filteredWord.size


}