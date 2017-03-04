package tfm.muuinf.viciano.lledo.alejandro.inurse.gui.comun;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import tfm.muuinf.viciano.lledo.alejandro.inurse.gui.personal.ConfigurarAvisosActivity;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ConfigurarAvisosActivity activity = (ConfigurarAvisosActivity) getActivity();
        if (minute < 10) {
            activity.setTime(hourOfDay + ":0" + minute);
        } else {
            activity.setTime(hourOfDay + ":" + minute);
        }
    }
}
