from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.

from rest_framework import generics
from rest_framework.permissions import AllowAny
from .serializers import UserSerializer
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

from django.contrib.auth import get_user_model   # ① add this
User = get_user_model()                          # ② add this

class RegisterView(generics.CreateAPIView):
    queryset = User.objects.all()
    permission_classes = (AllowAny,)
    serializer_class = UserSerializer



def home(request):
    return HttpResponse("welcome to API HOMEPAGE!!")

	
