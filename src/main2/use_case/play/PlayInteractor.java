package main2.use_case.play;
import main2.data_access.ResultRecordingDAO;
import main2.data_access.TriviaDBInterface;
import main2.entities.Player;
import main2.entities.Question;
import main2.entities.QuestionList;
import main2.use_case.settings.SettingsDTO;
import main2.use_case.settings.SettingsInteractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import java.beans.PropertyChangeListener;
public abstract class PlayInteractor implements PlayInputBoundary, PropertyChangeListener {
    protected final PlayOutputBoundary playOutputBoundary;
    protected final TriviaDBInterface questionGenerator;
    protected final ResultRecordingDAO resultRecordingDAO;


    protected QuestionList questionList;
    protected Question question;
    protected SettingsDTO settingsDTO;

    public PlayInteractor(PlayOutputBoundary playOutputBoundary,
                          SettingsInteractor settingsInteractor,
                          TriviaDBInterface questionGenerator,
                          ResultRecordingDAO resultRecordingDAO) {
        this.playOutputBoundary = playOutputBoundary;
        settingsInteractor.addPropertyChangeListener(this);
        settingsInteractor.firePropertyChanged();
        this.questionGenerator = questionGenerator;
        this.resultRecordingDAO = resultRecordingDAO;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        settingsDTO = (SettingsDTO) evt.getNewValue();
    }
}
