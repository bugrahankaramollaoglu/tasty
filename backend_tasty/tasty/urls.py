from django.urls import path
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)
from .views import RegisterView
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import FoodViewSet

router = DefaultRouter()
router.register(r'foods', FoodViewSet)

urlpatterns = [
    path('token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    path('api/', include(router.urls)),
    path('register/', RegisterView.as_view(), name='register'),
]
