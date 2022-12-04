package org.ticketkings.movietheatresystem.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data object that represents an email to be sent to a recipient
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String recipient;
    private String subject;
    private String msgBody;

}
