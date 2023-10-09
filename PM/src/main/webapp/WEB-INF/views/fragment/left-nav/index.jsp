<nav class="nav-bar bg-light border-end">
      <header class="text-center p-4 border-bottom">
        <a href="/PM/domain/info?id=${selectedDomain.id}" class="fs-4">
          <i class="home icon"></i>
        </a>
      </header>
      <div class="ui secondary vertical menu w-100 mt-5">
        <a href="/PM/plan?domain-id=${selectedDomain.id}" target="_blank" class="item">Plan</a>
        <div class="ui dropdown item">
          <i class="dropdown icon"></i>
          <span class="item-name">Db</span>
          <div class="menu">
            <a href="/PM/domain/erd?domain-id=${selectedDomain.id}" class="item">ERD Diagram</a>
            <a href="/PM/sqlquery?domain-id=${selectedDomain.id}" class="item">SQL queries</a>
          </div>
        </div>
        <div class="ui dropdown item">
          <i class="dropdown icon"></i>
          <span class="item-name">Flow</span>
          <div class="menu">
            <a href="/PM/screen?domain-id=${selectedDomain.id}" class="item">Screen</a>
            <a class="item">Usecase Specification</a>
          </div>
        </div>
        <div class="ui dropdown item">
          <i class="dropdown icon"></i>
          <span class="item-name">Class</span>
          <div class="menu">
            <a href="/PM/domain/class?domain-id=${selectedDomain.id}" class="item">Class Diagram</a>
            <a class="item">Packages</a>
            <a class="item">Class Specification</a>
            <a class="item">Unit Testing</a>
          </div>
        </div>
        <div class="ui dropdown item">
          <i class="dropdown icon"></i>
          <span class="item-name">SP</span>
          <div class="menu">
            <a class="item">Image</a>
          </div>
        </div>
      </div>
    </nav>