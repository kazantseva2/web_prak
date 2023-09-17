# Кадровое агентство
# База данных

![bd_prak](https://github.com/kazantseva2/web_prak/blob/main/bd_prak.svg)

# Схема страниц
![shema_prak.pdf](https://github.com/kazantseva2/web_prak/blob/main/shema_prak.svg)


## Для соискателя
### Главная страница для соискателя:
  - ссылка на главную страницу для работодателя
  - ссылка на страницу регистрации
  - ссылка на страницу входа
### Страница регестрации для соискателя:
  - ссылкка на главную страницу соискателя
  - ссылка на страницу регистрации
### Страница входа для соискателя:
  - ссылкка на главную страницу соискателя
  - ссылка на страницу регистрации
  - вход на личную страницу
## Далее все ниже описанные страницы имею переход на главную страрицу для соискателя(выход), личную страницу и страницу со списком вакансий
### Личная страница соикателя:
  - ссылкка на главную страницу соискателя
  - информация о соискателе
  - кнопки для редактирования информации
  - кнопка для удалениия личной страницы
  - ссылка на страницу откликов
### Страница редактирования
  - форма для редактирования
  - кнопка обновить
### Список вакансий:
  - список вакансий
  - кнопка фильтрации
  - ссылка на страницу вакансии
### Страница вакансии (соиск-ля):
  - информацтя о вакансии
  - ссылка на компанию, которая предлагает эту вакансию
  - кнопка откликнуться
### Страница с инф. о компании:
  - информация о кампании
### Страница откликов соискателя:
  - список откликов соискателя
  - ссылка на вакансию в отклике
  - кнопка для удаления отклика
### Страница со списком предыдущих работ соискателя:
  - список пред. работ соискателя
  - кнопка для добавления работы
  - кнопка для удаления работы
### Страница для добавления работы:
  - форма для добавления
  - кнопка добавить
 
## Для работодателя
### Главная страница для работодателя:
  - ссылкка на главную страницу соискателя
  - ссылка на страницу регистрации для работодателя или личную страницу
  - ссылка на список соискателей (если зарегестрирован)
### Список соискателей:
  - ссылкка на главную страницу работодателя
  - список соискателей
  - кнопка фильрации
  - ссылка на страницу с инф. о соискателе
### Страница с инф. о соискателе:
  - ссылкка на главную страницу работодателя
  - информация о соискателе
### Страница регестрации для работодателя:
  - ссылкка на главную страницу работодателя
  - поля для регистрации
### Личная страница работодателя:
  - ссылкка на главную страницу работодателя
  - информация о работодателе
  - кнопки для редактирования информации о себе
  - список предлагаемых вакансий
  - кнопка для удаления для каждой вакансии
  - кнопка для добавления вакансии
  - ссылка на страницу вакансии
### Страница запросов от соискателей:
  - ссылкка на главную страницу работодателя
  - список запросов
  - кнопка фильтрации
  - ссылка на страницу соискателя
### Страница вакансии (раб-ля):
  - ссылкка на главную страницу работодателя
  - кнопки для редактирования
  - ссылка на страницу запросов от соискателей

# Сценарии использования
#### Получение списка резюме по образованию, компаниям, в которых люди работали, по занимавшимся должностям, зарплатам:
Зарегестрированые работодатели могут с главной страницы работодателя перейти на страницу со списком соискателей и выставить необходимые фильтры.
#### Получение списка вакансий по компаниям, должностям, зарплатам:
Соскатели могут (даже без регистрации) с главной страницы соискателя перейти на страницу со списком вакансий, выставить необходимые фильтры, перейти на страницу вакансии и откликнуться.
#### Получение истории работы для данного человека:
Работодатель может перейти с главной страницы либо на страницу со списком соискателей, а затем на страницу соискателя, где и будет информации об истории работы для данного человека, либо перейти в личный кабинет, затем на страницу с запросами от соискателей, а затем, аналогично предыдущему случаю, перейти на страницу рассматриваемого работника.
#### Поиск подходящих вакансий и подходящих резюме:
Для поиска подходящих вакансий можно на странице с вакансиями выставить фильтр. Для поиска подходящих резюме можно на странице со списком соскателей выставить фильтры.
#### Добавление и удаление данных о человеке, чтение и редактирование данных о нем, добавление данных о новом трудоустройстве:
Все это можно сделать на личной странице соискателя, нажав на кнопку редактирования.
#### Добавление и удаление компании, чтение и редактирование данных о них, добавление, удаление и редактирование вакансий:
Редактирование информации о компании можно сделать на личной странице. Для редактирования вакансий надо с личной страницы работодалеля перейти на страницу необходимой вакансии и, нажав на кнопку редактирования, сделать изменеия, чтобы удалить вакансию надо на личной странице напротив вакансии нажать кнопку удалить.
