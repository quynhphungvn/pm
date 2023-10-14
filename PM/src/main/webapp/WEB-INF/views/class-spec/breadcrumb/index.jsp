<div class="ui breadcrumb ms-4">
	<a href="/PM/project?id=${selectedProject.id}" class="section">${selectedProject.name}</a>
	<div class="divider">/</div>
	<a href="/PM/domain/info?id=${selectedDomain.id}" class="section">${selectedDomain.name}</a>
	<div class="divider">/</div>
	<div class="active section">${selectedPackage.name}</div>
	<div class="divider">/</div>
	<div class="active section">${selectedClass.name}</div>
	<div class="divider">/</div>
	<div class="active section">${selectedTestingFunction.name}</div>
</div>
