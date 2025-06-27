from django.db import models

# Create your models here.

class Food(models.Model):
    name = models.CharField(max_length=255)
    image_name = models.CharField(max_length=255)
    price = models.DecimalField(max_digits=10, decimal_places=2)
    is_favourite = models.BooleanField(default=False)
    is_free_delivery = models.BooleanField(default=False)

    def __str__(self):
        return self.name
