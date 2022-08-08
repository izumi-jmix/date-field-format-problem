package org.izumi.jmix.dffp.screen.sample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Stream;

import io.jmix.ui.component.DateField;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("SampleScreen")
@UiDescriptor("sample-screen.xml")
public class SampleScreen extends Screen {
    private static final Logger log = LoggerFactory.getLogger(SampleScreen.class);

    @Autowired
    private DateField<LocalDate> a;
    @Autowired
    private DateField<LocalDate> b;
    @Autowired
    private DateField<LocalDate> c;
    @Autowired
    private DateField<LocalDate> d;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        final var now = LocalDate.now();

        Stream.of(a, b, c, d).forEach(dateField -> {
            final var dateFormat = dateField.getDateFormat();
            if (Objects.isNull(dateFormat)) {
                return;
            }

            final var formatter = DateTimeFormatter.ofPattern(dateFormat);
            log.warn(now.format(formatter));

            dateField.setValue(now);
        });
    }
}