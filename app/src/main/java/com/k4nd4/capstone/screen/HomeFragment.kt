package com.k4nd4.capstone.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.k4nd4.capstone.databinding.FragmentHomeBinding
import com.k4nd4.capstone.viewmodel.HomeViewModel
import com.k4nd4.core.domain.model.Student
import com.k4nd4.core.presentation.rvadapter.StudentRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.students.observe(viewLifecycleOwner) { student ->
            setUpRecyclerView(student)
        }
    }

    private fun setUpRecyclerView(students: List<Student>) {
        val rvAdapter = StudentRecyclerViewAdapter(students) {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra(DetailActivity.STUDENT, it)
            startActivity(intent)
        }
        val rvLayoutManager = GridLayoutManager(context, 2)
        binding.homeRv.apply {
            adapter = rvAdapter
            layoutManager = rvLayoutManager
        }
    }

}