package com.example.rickandmortyapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.example.rickandmortyapp.databinding.FragmentPage2Binding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp


class Page2Fragment : Fragment() {

    private var _binding: FragmentPage2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPage2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            MyUI()
        }

        binding.copyAuthorMailBtn.setOnClickListener {
            val email = "wojciechrudol@gmail.com"
            val clipboardManager =
                it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("label", email)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(it.context, "Copied: $email", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun MyUI() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.pngwing),
            contentDescription = "Dog Image",
            modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = R.string.app_author_wojciech_rudol), fontSize = 20.sp)

        Spacer(modifier = Modifier.height(3.dp))
        Text(text = stringResource(id = R.string.contact_wojciechrudol_gmail_com), fontSize = 20.sp)
    }
}


/*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

            }
        }
    }
 */


/*

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun defaultPreview() {
    Display_Image {
        Greeting("Adolf")
    }
}



        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val imageModifier = Modifier
                        .size(150.dp)
                        .border(BorderStroke(1.dp, Color.Black))
                        .background(Color.Yellow)
                    Image(
                        painter = painterResource(id = R.drawable.pngwing),
                        contentDescription = stringResource(id = R.string.dog_content_description),
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier
                    )
                }
            }
        }




        binding?.copyAuthorMailBtn?.setOnClickListener {
            val email = "wojciechrudol@gmail.com"
            val clipboardManager =
                it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("label", email)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(it.context, "Copied: $email", Toast.LENGTH_SHORT).show()
        }
    }
 */


