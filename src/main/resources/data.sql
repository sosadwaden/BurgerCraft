delete
from Ingredient_Ref;

delete
from Burger;

delete
from Burger_Order;

delete
from Ingredient;

insert into Ingredient (id, name, type)
values ('BLBR', 'Чёрный хлеб', 'WRAP');

insert into Ingredient (id, name, type)
values ('WHBR', 'Белый хлеб', 'WRAP');

insert into Ingredient (id, name, type)
values ('CHIC', 'Курица', 'PROTEIN');

insert into Ingredient (id, name, type)
values ('GRBF', 'Говяжий фарш', 'PROTEIN');

insert into Ingredient (id, name, type)
values ('TMTO', 'Помидоры', 'VEGGIES');

insert into Ingredient (id, name, type)
values ('LETC', 'Салат', 'VEGGIES');

insert into Ingredient (id, name, type)
values ('CHED', 'Чеддер', 'CHEESE');

insert into Ingredient (id, name, type)
values ('KETC', 'Кетчуп', 'SAUCE');

insert into Ingredient (id, name, type)
values ('SLSA', 'Сальса', 'SAUCE');