package com.projektpo.wiorektrepka.budget.domain;

import com.projektpo.wiorektrepka.budget.util.CodeEventBuilder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class CodeEvent {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "code_event_id")
	private long codeEventId;
	@NotEmpty
	private String code;
	@NotNull
	private AppEvent event;
	@NotNull
	private Long userId;

	public static CodeEventBuilder builder() {
		return new CodeEventBuilder();
	}
}
