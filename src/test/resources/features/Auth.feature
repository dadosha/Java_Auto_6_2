#language:ru

Функциональность: Перевод с карты на карту

  Предыстория: Логин
    Пусть открыта страница с формой авторизации "http://localhost:9999"
    Когда пользователь пытается авторизоваться с именем "vasya" и паролем "qwerty123"
    И пользователь пытается ввести код подтверждения "12345"

  Структура сценария:
    Когда пользователь переводит <amount> рублей с карты с номером <cardFromNumber> на свою <numberListCardTo> карту с главной страницы
    Тогда баланс его <numberListCardTo> карты из списка на главной странице должен стать <balanceToAfter> рублей
    Примеры:
      | amount | cardFromNumber        | numberListCardTo | balanceToAfter |
      | 5000   | "5559 0000 0000 0002" | 1                | 15000          |
