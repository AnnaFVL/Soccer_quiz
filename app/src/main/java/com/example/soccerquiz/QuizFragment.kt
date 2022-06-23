package com.example.soccerquiz

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.soccerquiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private val quizItems: MutableList<QuizItem> = mutableListOf(
        /*QuizItem("How many players does each team have on the pitch when a soccer match starts?",
            listOf("11", "8", "12")),
        QuizItem("What is given to a player for a very serious personal foul on an opponent?",
            listOf("Red Card", "Green Card", "Yellow Card")),
        QuizItem("To most places in the world, soccer is known as what?",
            listOf("Football", "Footgame", "Legball")),*/
        QuizItem("4 * 4 =", listOf("16", "8", "12")),
        QuizItem("6 * 6 =", listOf("36", "26", "28")),
        QuizItem("7 * 7 =", listOf("49", "42", "47")),
        QuizItem("8 * 8 =", listOf("64", "62", "68")),
        QuizItem("9 * 9 =", listOf("81", "92", "49")),
        QuizItem("6 * 3 =", listOf("18", "16", "14")),
        QuizItem("3 * 7 =", listOf("21", "24", "18")),
        QuizItem("8 * 3 =", listOf("24", "21", "27")),
        QuizItem("3 * 9 =", listOf("27", "28", "26")),
        QuizItem("4 * 6 =", listOf("24", "25", "26")),
        QuizItem("7 * 4 =", listOf("28", "26", "27")),
        QuizItem("4 * 8 =", listOf("32", "24", "26")),
        QuizItem("9 * 4 =", listOf("36", "32", "34")),
        QuizItem("5 * 6 =", listOf("30", "25", "32")),
        QuizItem("7 * 5 =", listOf("35", "37", "30")),
        QuizItem("5 * 8 =", listOf("40", "45", "35")),
        QuizItem("9 * 5 =", listOf("45", "46", "49")),
        QuizItem("6 * 7 =", listOf("42", "46", "49")),
        QuizItem("8 * 6 =", listOf("48", "49", "54")),
        QuizItem("6 * 9 =", listOf("54", "56", "52")),
        QuizItem("7 * 8 =", listOf("56", "54", "52")),
        QuizItem("9 * 7 =", listOf("63", "64", "65")),
        QuizItem("8 * 9 =", listOf("72", "68", "74"))
    )

    lateinit var currentQuizItem: QuizItem
    lateinit var answers: MutableList<String>
    private var quizItemIndex = 0
    private val numberOfQuestions = 5


/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_quiz, container, false)
        val bindind = DataBindingUtil.inflate<FragmentQuizBinding>(inflater, R.layout.fragment_quiz, container, false)

        getRandomQuizItem()

        bindind.quizFragment = this

        bindind.passButton.setOnClickListener{ view: View ->

            val selectedCheckboxId = bindind.quizRadioGroup.checkedRadioButtonId
            if (selectedCheckboxId != -1) {
                var answerIndex = 0
                when (selectedCheckboxId) {
                    R.id.firstRadioButton -> answerIndex = 0
                    R.id.secondRadioButton -> answerIndex = 1
                    R.id.thirdRadioButton -> answerIndex = 2
                }
                if (answers[answerIndex] == currentQuizItem.answersList[0]) {
                    quizItemIndex++
                    if (quizItemIndex < numberOfQuestions) {
                        setQuizItem()
                        bindind.invalidateAll()
                    }
                    else {
                        // goal
                        view.findNavController().navigate(R.id.action_quizFragment_to_goalFragment)
                    }
                }
                else {
                    // miss
                    view.findNavController().navigate(R.id.action_quizFragment_to_missFragment)
                }
            }

        }

        (activity as AppCompatActivity).supportActionBar?.title = "Soccer quiz"

        setHasOptionsMenu(true)

        return bindind.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }

    private fun getRandomQuizItem() {
        quizItems.shuffle()
        quizItemIndex = 0
        setQuizItem()

    }

    private fun setQuizItem() {
        currentQuizItem = quizItems[quizItemIndex]
        answers = currentQuizItem.answersList.toMutableList()
        answers.shuffle()
    }

/*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}