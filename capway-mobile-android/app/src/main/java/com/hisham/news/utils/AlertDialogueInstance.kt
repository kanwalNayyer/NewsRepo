package com.hisham.news.utils

import android.content.Context
import android.text.Html
import androidx.appcompat.app.AlertDialog


public class AlertDialogue
{
    public fun showDialogue(context: Context,title:String, msg:String)
    {
        val builder = AlertDialog.Builder(context)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(Html.fromHtml(msg))
        builder.setPositiveButton("Close"){dialogInterface, which ->
            dialogInterface.cancel()
            //alertDialog.dismiss()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()

    }

}