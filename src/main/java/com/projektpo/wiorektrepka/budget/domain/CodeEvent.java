package com.projektpo.wiorektrepka.budget.domain;

import com.projektpo.wiorektrepka.budget.util.CodeEventBuilder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CodeEvent {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "code_event_id")
	private long codeEventId;
	private String code;
	private AppEvent event;
	private long userId;

	public static CodeEventBuilder builder() {
		return new CodeEventBuilder();
	}
}
