# Task 4
# Condition: 3.2.34 Связывание. Реализуйте расширенный API ThreadedST, который поддерживает две дополнительные операции, выполняемые за константное время:
# * Key next(Key key) ключ, следующий за key(null, если key наибольший); 
# * Key prev(Key key) ключ, следующий за key(null, если key наименьший);
# Для этого добавьте в структуру Node поля pred и  succ, содержащие ссылки на предшествующий и последующий узлы, и добавьте в методы put(), deleteMin(), deleteMax() и delete() поддержку этих полей.
# Plan: 1h
# Fact: 1h