package it.d0ge01.cigarettecounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class Dialogo extends DialogFragment {
    private MenuFragment obb;
    
@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the Builder class for convenient dialog construction
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setMessage(R.string.secureReset)
           .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   setZeroN();
               }
           })
           .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                   // User cancelled the dialog
               }
           });
    // Create the AlertDialog object and return it
    return builder.create();
}

public void setOb(MenuFragment activity) {
        this.obb = activity;
}

private void setZeroN() {
        this.obb.setZero();
}
}