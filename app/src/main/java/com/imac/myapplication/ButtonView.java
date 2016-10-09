/*******************************************************************************
 * Copyright 2013 Marian Schedenig
 *
 * This file is part of n Tile Puzzle.
 *
 * n Tile Puzzle is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * n Tile Puzzle is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * n Tile Puzzle. If not, see http://www.gnu.org/licenses/.
 *******************************************************************************/
package com.imac.myapplication;

import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class ButtonView extends FrameLayout
{
	private View contentView;

	public ButtonView(final PuzzleActivity context, View contentView)
	{
		super(context);
		
		this.contentView = contentView;
		contentView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(contentView);
		
		RelativeLayout overlay = new RelativeLayout(context);
		overlay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		addView(overlay);
		
		if(!hasPermanentMenuKey())
		{
			final ImageButton btnMenu = new ImageButton(getContext());
			btnMenu.setImageResource(android.R.drawable.ic_menu_more);
			
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			btnMenu.setLayoutParams(layoutParams);
			btnMenu.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					context.openContextMenu(btnMenu);
				}
			});
			
			context.registerForContextMenu(btnMenu);
			overlay.addView(btnMenu);
		}
	}

	private boolean hasPermanentMenuKey()
	{
		if(android.os.Build.VERSION.SDK_INT < 11)
		{
			return true;
		}
		else if(android.os.Build.VERSION.SDK_INT < 14)
		{
			return false;
		}
		else
		{
			return ViewConfiguration.get(getContext()).hasPermanentMenuKey();
		}
	}

	public View getContentView()
	{
		return contentView;
	}
}
