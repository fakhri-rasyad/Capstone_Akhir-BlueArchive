package com.k4nd4.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.presentation.rvadapter.StudentRecyclerViewAdapter
import com.k4nd4.core.utils.IntentProvider
import com.k4nd4.core.utils.showToast
import com.k4nd4.favorite.FavoriteViewModel
import com.k4nd4.favorite.R
import com.k4nd4.favorite.databinding.FragmentFavoriteBinding
import com.k4nd4.favorite.favoriteModule
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteFragment() : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel : FavoriteViewModel by viewModel()
    private lateinit var rvAdapter: StudentRecyclerViewAdapter
    private val intent: IntentProvider by inject()

    init {
        loadKoinModules(listOf(
            favoriteModule
        ))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = StudentRecyclerViewAdapter(listOf()){

        }
        viewModel.students.observe(viewLifecycleOwner) {
            setUpRecyclerView(it)
        }
    }

    override fun onResume() {
        super.onResume()
        if(rvAdapter.itemCount == 0){
            showToast(getString(R.string.no_student_err), requireContext())
        }
    }

    private fun setUpRecyclerView(students: List<Student>){
        rvAdapter = StudentRecyclerViewAdapter(students){
            val intent = intent.getDetailActivityIntent(requireContext(), it)
            startActivity(intent)
        }
        val rvLayoutManager = GridLayoutManager(context, 2)
        binding.homeRv.apply {
            adapter = rvAdapter
            layoutManager = rvLayoutManager
        }
    }
}