package co.tiagoaguiar.course.instagram.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import co.tiagoaguiar.course.instagram.R

class CustomDialog(context: Context): Dialog(context) {

    private lateinit var dialogLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_custom)

        dialogLinearLayout = findViewById<LinearLayout>(R.id.dialog_container)
    }

    fun addButton(listener: View.OnClickListener, vararg texts: Int){

    }


}