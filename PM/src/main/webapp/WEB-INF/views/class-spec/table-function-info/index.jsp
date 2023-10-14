<table class="ui definition table">
	<tbody>
		<tr>
			<td>Function name</td>
			<td>${selectedTestingFunction.name}</td>
		</tr>
		<tr>
			<td>Test Requirement</td>
			<td>${selectedTestingFunction.testRequirement }</td>
			<td></td>
		</tr>
		<tr>
			<td>Input</td>
			<td>${selectedTestingFunction.functionParams}</td>
		</tr>
		<tr>
			<td>Out put</td>
			<td>
				<table class="ui definition table">
					<tbody>
						<tr>
							<td>Return</td>
							<td>${selectedTestingFunction.functionReturn}</td>
						</tr>
						<tr>
							<td>Exception</td>
							<td>${selectedTestingFunction.functionExceptions }</td>
						</tr>
						<tr>
							<td>Logs</td>
							<td>${selectedTestingFunction.functionLogs}</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>
