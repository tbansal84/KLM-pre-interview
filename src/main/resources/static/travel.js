$(document)
		.ready(
				function() {

					$('select').selectstyle({
						width : 400,
						height : 300,
						theme : 'light',
						onchange : function(val) {
						}
					});

					$('#btn_submit')
							.on(
									"click",
									function(e) {
										e.preventDefault();
										var selectedOrigin = $(
												'div#select_style_text').get(0).innerText;
										var selectedDestination = $(
												'div#select_style_text').get(1).innerText;
										var isOriginNotSelected = (selectedOrigin === "Select Your Origin") ? true
												: false;
										var isDestinationNotSelected = (selectedDestination === "Select Your Destination") ? true
												: false;
										if (isOriginNotSelected
												&& isDestinationNotSelected) {
											alert("Please select Origin and Destination ");
										} else if (isOriginNotSelected) {
											alert("Origin cannot be empty");
										} else if (isDestinationNotSelected) {
											alert("Destination cannot be empty");
										} else if ($.trim(selectedOrigin) === $
												.trim(selectedDestination)) {
											alert("Origin and Destination cannot be same");
										} else {
											var regExp = /\(([^)]+)\)/;
											var originCode = regExp
													.exec(selectedOrigin)[1];
											var destinationCode = regExp
													.exec(selectedDestination)[1];
											$
													.ajax({
														type : "GET",
														url : "/travel/plan/"
																+ originCode
																+ "/"
																+ destinationCode,
														timeout : 60000,
														success : function(
																response) {
															$("#resultTrace")
																	.hide();
															$(
																	"#result tbody tr")
																	.html("");
															$(
																	"#result tbody tr")
																	.append(
																			"<td>"
																					+ response.amount
																					+ "</td>"
																					+ "<td>"
																					+ response.currency
																					+ "</td>"
																					+ "<td>"
																					+ response.origin.code
																					+ "</td>"
																					+ "<td>"
																					+ response.destination.code
																					+ "</td>");
															$("#result").show();
														}
													});
										}
									});

					$('#link_trace')
							.on(
									"click",
									function(e) {
										e.preventDefault();
										$
												.ajax({
													type : "GET",
													url : "/stats",
													timeout : 60000,
													success : function(response) {
														$("#result").hide();
														$(
																"#resultTrace tbody tr")
																.html("");
														$(
																"#resultTrace tbody tr")
																.append(
																		"<td>"
																				+ response.totalRequests
																				+ "</td>"
																				+ "<td>"
																				+ response.totalOKRequests
																				+ "</td>"
																				+ "<td>"
																				+ response.total4XXRequests
																				+ "</td>"
																				+ "<td>"
																				+ response.total5XXRequests
																				+ "</td>"
																				+ "<td>"
																				+ response.avgResponseTime
																				+ "</td>"
																				+ "<td>"
																				+ response.minResponseTime
																				+ "</td>"
																				+ "<td>"
																				+ response.maxResponseTime
																				+ "</td>");
														$("#resultTrace")
																.show();
													}
												});

									});

				});
