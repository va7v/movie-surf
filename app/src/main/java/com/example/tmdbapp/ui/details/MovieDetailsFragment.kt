package com.example.tmdbapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.buttomnavigation.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsFragment(): Fragment() {

    private lateinit var movieDetailsModel: MovieDetailsModel
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailsModel = ViewModelProvider(this).get(MovieDetailsModel::class.java)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewDetails: TextView = binding.textNotifications
        // val textViewActor: TextView = binding.textActor
        val imageView: ImageView = binding.imageView
        Picasso.get().load(BASE_IMAGE_URL +
             arguments?.getString("poster_path")).into(imageView)

        movieDetailsModel.getMovieGentre(arguments?.getString("movie_id"))
        movieDetailsModel.text.observe(getViewLifecycleOwner(), Observer {
            var gentre = it
            textViewDetails.text = arguments?.getString("title")+"  /  Дата выхода: " +
                    arguments?.getString("date")+"\nРейтинг: "+
                    arguments?.getString("rate") + "\nЖанр: " + gentre +
                    "\nОписание:  " + arguments?.getString("overview")
        })

        movieDetailsModel.getMovieActors(arguments?.getString("movie_id"))

        movieDetailsModel.items.observe(getViewLifecycleOwner(), Observer {
/*            val size = it.size - 1
            var str = "Роли играли:\n${it.get(0).name}"
            for (n in 1 .. size) { str = str + ", " + it.get(n).name}
            textViewActor.text = str*/
            val linLayout: LinearLayout = binding.linLayout
            for (i in 0 until it.size) {
                val item = LayoutInflater.from(linLayout.context).inflate(com.example.buttomnavigation.R.layout.item, null)
                val tvName = item.findViewById<View>(com.example.buttomnavigation.R.id.tv_name) as TextView
                tvName.text = it[i].name
                val cha = item.findViewById<View>(com.example.buttomnavigation.R.id.cha) as TextView
                cha.text = "As: " + it[i].character
                val imageViewCast = item.findViewById<View>(com.example.buttomnavigation.R.id.image_view_cast) as ImageView
                Picasso.get().load(BASE_IMAGE_URL + it[i].profile_path).into(imageViewCast)

                linLayout.addView(item)
            }

        })

        movieDetailsModel.status.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }
}