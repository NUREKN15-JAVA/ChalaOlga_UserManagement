package com.olgachala.usermanagement.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;

import com.olgachala.usermanagement.User;
import com.olgachala.usermanagement.db.DatabaseExñeption;
import com.olgachala.usermanagement.util.Messages;

public class EditPanel extends AddPanel {
	
	private User user;

    public EditPanel(MainFrame parent) {
        super(parent);
        setName("editPanel"); //$NON-NLS-1$
    }

    protected void doAction(ActionEvent e) throws ParseException {
        System.out.println(user);
        if ("ok".equalsIgnoreCase(e.getActionCommand())) { //$NON-NLS-1$
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
            try {
                Date date = format.parse(getDateOfBirthField().getText());
                user.setDate(date);
            } catch (ParseException e1) {
                getDateOfBirthField().setBackground(Color.RED);
                throw e1;
            }
            try {
                parent.getDao().update(user);
            } catch (DatabaseExñeption e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), Messages.getString("EditPanel.error"), //$NON-NLS-1$
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void setUser(User user) {
        DateFormat format = DateFormat.getDateInstance();
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getLastNameField().setText(user.getLastName());
        getDateOfBirthField().setText(format.format(user.getDate()));
    }
}