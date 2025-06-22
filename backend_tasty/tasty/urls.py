from django.urls import path
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)
from .views import RegisterView, UserDetailView

urlpatterns = [
    # JWT login endpoint (returns access & refresh tokens)
    path('token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    # JWT refresh token endpoint
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),

    # Signup endpoint
    path('register/', RegisterView.as_view(), name='register'),

    # User info endpoint (needs authentication)
    path('me/', UserDetailView.as_view(), name='user_detail'),
]
