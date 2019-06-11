package com.coffee.myadventure.game_space

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.coffee.myadventure.R
import com.coffee.myadventure.database.GameDatabase
import com.coffee.myadventure.databinding.GameDetectiveFragmentBinding

class GameDetectiveFragment : Fragment() {

    private lateinit var gameDetectiveViewModel: GameDetectiveViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: GameDetectiveFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.game_detective_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = GameDatabase.getInstance(application).gameDatabaseDao

        val viewModelFactory = GameDetectiveViewModelFactory(dataSource, application, R.raw.bensoundenigmatic)

        gameDetectiveViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(GameDetectiveViewModel::class.java)

        binding.gameDetectiveViewModel = gameDetectiveViewModel

        binding.setLifecycleOwner(this)

        // Add an Observer on the state variable for Navigating when STOP button is pressed.
        gameDetectiveViewModel.navigateToGameScore.observe(this, Observer { game ->
            game?.let {
                this.findNavController().navigate(
                    GameDetectiveFragmentDirections
                        .actionGameDetectiveFragmentToGameDetectiveWonFragment(game.gameId)
                )
                gameDetectiveViewModel.doneNavigating()
            }
        })

        gameDetectiveViewModel.questionString.observe(this, Observer { image ->
            image?.let {
                when(image){
                    "cellphone" -> binding.questionView.setBackgroundResource(R.drawable.im_detective_1)
                    "camera" -> binding.questionView.setBackgroundResource(R.drawable.im_detective_2)
                    "map" -> binding.questionView.setBackgroundResource(R.drawable.im_detective_3)
                    else -> binding.questionView.setBackgroundResource(R.drawable.im_detective_1)
                }
            }
        })

        gameDetectiveViewModel.answerString.observe(this, Observer { image ->
            image?.let {
                if(image.isNotEmpty()) {
                    when (image[0]) {
                        "cellphone" -> binding.btAnswerOne.setBackgroundResource(R.drawable.im_detective_answer_1)
                        "camera" -> binding.btAnswerOne.setBackgroundResource(R.drawable.im_detective_answer_2)
                        "map" -> binding.btAnswerOne.setBackgroundResource(R.drawable.im_detective_answer_3)
                        else -> binding.btAnswerOne.setBackgroundResource(R.drawable.im_detective_answer_1)
                    }
                    when (image[1]) {
                        "cellphone" -> binding.btAnswerTwo.setBackgroundResource(R.drawable.im_detective_answer_1)
                        "camera" -> binding.btAnswerTwo.setBackgroundResource(R.drawable.im_detective_answer_2)
                        "map" -> binding.btAnswerTwo.setBackgroundResource(R.drawable.im_detective_answer_3)
                        else -> binding.btAnswerTwo.setBackgroundResource(R.drawable.im_detective_answer_1)
                    }
                    when (image[2]) {
                        "cellphone" -> binding.btAnswerThree.setBackgroundResource(R.drawable.im_detective_answer_1)
                        "camera" -> binding.btAnswerThree.setBackgroundResource(R.drawable.im_detective_answer_2)
                        "map" -> binding.btAnswerThree.setBackgroundResource(R.drawable.im_detective_answer_3)
                        else -> binding.btAnswerThree.setBackgroundResource(R.drawable.im_detective_answer_1)
                    }
                }
            }
        })

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        gameDetectiveViewModel.onPause()
    }

    override fun onResume() {
        super.onResume()
        gameDetectiveViewModel.onResume()
    }
}
