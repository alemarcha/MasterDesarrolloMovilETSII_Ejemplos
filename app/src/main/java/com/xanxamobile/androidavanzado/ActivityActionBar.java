package com.xanxamobile.androidavanzado;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ActivityActionBar extends Activity{

	

	//=================================================
	// Constants
	//=================================================

	//=================================================
	// Fields
	//=================================================

	//=================================================
	// Constructors
	//=================================================
	
	//=================================================
	// override function
	//=================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//CONFIGURAMOS EL ACTION BAR
		ActionBar actionBar = getActionBar();
		//1. Hacemos que el button home tenga funcialidad
		actionBar.setHomeButtonEnabled(true);
		//2. Ponemos que el botón home tenga un símbolod de back
		actionBar.setHomeButtonEnabled(true);
		//3. Le cambiamos el título a la action bar:
		actionBar.setTitle("Action Bar Example");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_action_bar, menu);
	    MenuItem menuItem = menu.findItem(R.id.menu_search);
	    
	    configureMenuSearch(menuItem);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_add:
			
			break;
		case R.id.menu_delete:
			
			break;
		case R.id.menu_search:
			Toast.makeText(ActivityActionBar.this, "Search", Toast.LENGTH_SHORT).show();
			break;
		case android.R.id.home:
			finish();//Terminamos la activity y volvemos a la lista principal.
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//=================================================
	// Functions
	//=================================================
	/**
	 * Configuramos el menú search
	 * @param menuItem
	 */
	private void configureMenuSearch(MenuItem menuItem) {
		//Informamos de cuando se ha expandido y de cuando se contrae el menu de buscar:
		menuItem.setOnActionExpandListener(new OnActionExpandListenerMine());
		
		SearchView searchView = (SearchView)menuItem.getActionView();
		searchView.setOnQueryTextListener(new OnQueryTextListenerMine ());
	}
	//=================================================
	// Getters and Setters
	//=================================================

	//=================================================
	// Classes
	//=================================================
	public class OnActionExpandListenerMine implements OnActionExpandListener{

		public boolean onMenuItemActionCollapse(MenuItem item) {
			Toast.makeText(ActivityActionBar.this, "onMenuItemActionCollapse", Toast.LENGTH_SHORT).show();
			return true;
		}

		public boolean onMenuItemActionExpand(MenuItem item) {
			Toast.makeText(ActivityActionBar.this, "onMenuItemActionExpand", Toast.LENGTH_SHORT).show();
			return true;
		}
		
	}
	
	public class OnQueryTextListenerMine implements OnQueryTextListener{

		public boolean onQueryTextChange(String newText) {
			return false;
		}

		public boolean onQueryTextSubmit(String query) {
			Toast.makeText(ActivityActionBar.this, "Buscamos por "+query, Toast.LENGTH_SHORT).show();
			return false;
		}
		
	}
	//=================================================
	// Test
	//=================================================
}
