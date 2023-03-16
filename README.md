# Sprint_4

<h2>What is this repository for?</h2>
<ul>
<li> Automation testing project for the next service: <a href="https://qa-scooter.praktikum-services.ru/"> https://qa-scooter.praktikum-services.ru/ </a>
</li>
</ul>

<h2>Project structure</h2>
Main/java/ contains package 
<ul>
<li> "constants" -> constants values such text/ correct values/ URL links </li> 
<li> "objects" -> page objects that are divided by meaning and service pages </li>
</ul>

test/java/ contains tests
<ul>
<li> "MainFunctionTest" -> status order message </li> 
<li> "QuestionPartTest" -> Q&A section</li>
<li> "RedirectTest" -> redirect to pages</li> 
<li> "OrderTest" -> happy path - order</li>
<li> "OrderFieldErrorsTest" -> error messages for invalid values</li>
</ul>
